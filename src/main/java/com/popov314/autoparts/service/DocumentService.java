package com.popov314.autoparts.service;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DocumentService {

  private final JdbcTemplate jdbcTemplate;

  private static final Pattern SELECT_START_PATTERN = Pattern.compile("(?is)^\\s*SELECT.*");

  private static final Pattern DENIED_KEYWORDS_PATTERN = Pattern.compile(
      "(?im).*\\b(UPDATE|DELETE|INSERT|DROP|ALTER|TRUNCATE|GRANT|REVOKE|MERGE|REPLACE)\\b.*"
  );

  public List<Map<String, Object>> executeQuery(String sql) {

    validateSql(sql);

    return jdbcTemplate.queryForList(sql);
  }

  private void validateSql(String sql) {
    if (sql == null || sql.isBlank()) {
      throw new IllegalArgumentException("SQL запрос не может быть пустым");
    }

    // Защита от нескольких запросов в одной строке (SQL Injection chaining)
    if (sql.contains(";")) {
      throw new SecurityException("Символ ';' запрещен. Выполняйте запросы по одному.");
    }

    // Проверка, что запрос начинается с SELECT
    if (!SELECT_START_PATTERN.matcher(sql).matches()) {
      throw new SecurityException("Разрешены только SELECT запросы.");
    }

    // Проверка на наличие опасных слов внутри (даже в комментариях или подзапросах)
    if (DENIED_KEYWORDS_PATTERN.matcher(sql).matches()) {
      throw new SecurityException("Обнаружено запрещенное ключевое слово (UPDATE, DELETE и др.)");
    }
  }

  public String getPredefinedQuery(int id) {
    return switch (id) {
      case 1 -> """
          SELECT\s
          (SELECT SUM(product_quantity) FROM cell_inventory) AS "Общее кол-во деталей",
          (SELECT AVG(order_price) FROM orders) AS "Средняя цена заказа",
          (SELECT MAX(delivery_price) FROM deliveries) AS "Максимальная цена поставки",
          (SELECT MIN(delivery_price) FROM deliveries) AS "Минимальная цена поставки"

          """;
      case 2 -> """
              SELECT m.name AS "Производитель",\s
              COUNT(p.id) AS Всего,
              AVG(p.price) AS Средняя_Цена
              FROM products p
              JOIN manufactures m ON p.manufacture_id = m.id
              GROUP BY m.name
              """;
      case 3 -> """
          SELECT
          o.id,
          p.name AS "Название Детали",
          o.quantity AS "Количество",
          c.first_name AS "Имя заказчика",
          o.order_date AS "Дата заказа",
          o.order_price AS "Цена заказа"
          FROM orders o
          JOIN products p ON o.product_id = p.id
          JOIN clients c ON o.client_id = c.id
          ORDER BY order_price DESC, order_date ASC
          """;
      default -> "";
    };
  }

}
