package com.popov314.autoparts.service;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentService {

  private final JdbcTemplate jdbcTemplate;

  public List<Map<String, Object>> executeQuery(String sql) {
    return jdbcTemplate.queryForList(sql);
  }

  public String getPredefinedQuery(int id) {
    return switch (id) {
      case 1 -> """
          SELECT\s
          (SELECT SUM(product_id) FROM cell_inventory) AS "Общее кол-во деталей",
          (SELECT AVG(order_price) FROM orders) AS "Средняя цена заказа",
          (SELECT MAX(delivery_price) FROM deliveries) AS "Максимальная цена поставки",
          (SELECT MIN(delivery_price) FROM deliveries) AS "Минимальная цена поставки";

          """;
      case 2 -> """
              SELECT m.name AS "Производитель",\s
              COUNT(p.id) AS Всего,
              AVG(p.price) AS Средняя_Цена
              FROM products p
              JOIN manufactures m ON p.manufacture_id = m.id
              GROUP BY m.name;
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
          ORDER BY order_price DESC, order_date ASC;
          """;
      default -> "";
    };
  }

}
