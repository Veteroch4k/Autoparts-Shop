package com.popov314.autoparts.service;

import com.popov314.autoparts.repository.ProductRepository;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

  private final JdbcTemplate jdbcTemplate;

  //  Топ дорогих товаров
  public Map<String, Double> getTopExpensiveProducts() {
    String sql = "SELECT name, price FROM products ORDER BY price DESC LIMIT 10";
    Map<String, Double> data = new LinkedHashMap<>();
    try {
      jdbcTemplate.query(sql, rs -> {
        String name = rs.getString("name");
        if (name.length() > 15) name = name.substring(0, 15) + "...";
        data.put(name, rs.getDouble("price"));
      });
    } catch (Exception e) {
      System.err.println("Ошибка SQL (Top Products): " + e.getMessage());
    }
    return data;
  }

  //  Здесь Динамика продаж  с фильтром
  public Map<String, Integer> getSalesDynamics(int days) {
    String timeFilter = "";

    // Фильтр по времени (PostgreSQL синтаксис)
    if (days > 0) {
      timeFilter = "WHERE order_date > NOW() - INTERVAL '" + days + " DAY' ";
    }

    String sql = """
            SELECT TO_CHAR(order_date, 'YYYY-MM-DD') as date_str, COUNT(*) as cnt 
            FROM orders 
            """ + timeFilter + """
            GROUP BY date_str 
            ORDER BY date_str ASC
        """;

    Map<String, Integer> data = new LinkedHashMap<>();
    try {
      jdbcTemplate.query(sql, rs -> {
        data.put(rs.getString("date_str"), rs.getInt("cnt"));
      });
    } catch (Exception e) {
      System.err.println("Ошибка SQL (Sales): " + e.getMessage());
    }
    return data;
  }

  // тут Бренды
  public Map<String, Integer> getProductsByManufacture() {
    String sql = """
            SELECT m.name, COUNT(p.id) as cnt 
            FROM products p 
            JOIN manufactures m ON p.manufacture_id = m.id 
            GROUP BY m.name
        """;

    Map<String, Integer> data = new LinkedHashMap<>();
    try {
      jdbcTemplate.query(sql, rs -> {
        data.put(rs.getString("name"), rs.getInt("cnt"));
      });
    } catch (Exception e) {
      System.err.println("Ошибка SQL (Brands): " + e.getMessage());
    }
    return data;
  }

  // Топ популярных товаров по количеству продаж
  public Map<String, Integer> getTopSellingProducts() {
    String sql = """
            SELECT p.name, SUM(o.quantity) as total
                        FROM orders o
                        JOIN products p ON o.product_id = p.id
                        GROUP BY p.name
                        ORDER BY total DESC
                        LIMIT 5""";

    Map<String, Integer> data = new LinkedHashMap<>();
    try {
      jdbcTemplate.query(sql, rs -> {
        String name = rs.getString("name");
        if (name.length() > 20) name = name.substring(0, 20) + "...";
        data.put(name, rs.getInt("total"));
      });
    } catch (Exception e) {
      System.err.println("Ошибка SQL (Best Sellers): " + e.getMessage());
    }
    return data;
  }
}