package com.popov314.autoparts.controller.analytics;

import com.popov314.autoparts.service.AnalyticsService;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('DIRECTOR') or hasRole ('SALES')" )
public class AnalyticsRestController {

  private final AnalyticsService analyticsService;

  // 1. Данные для Area Chart (Продажи по времени)
  @GetMapping("/api/analytics/sales")
  public Map<String, Object> getSalesData(@RequestParam(defaultValue = "7") int days) {
    Map<String, Integer> data = analyticsService.getSalesDynamics(days);
    return prepareChartData(data);
  }

  // 2. Данные для Bar Chart (Топ цен)
  @GetMapping("/api/analytics/prices")
  public Map<String, Object> getPricesData() {
    Map<String, Double> data = analyticsService.getTopExpensiveProducts();
    return prepareChartData(data);
  }

  // 3. Данные для Pie Chart (Бренды)
  @GetMapping("/api/analytics/brands")
  public Map<String, Object> getBrandsData() {
    Map<String, Integer> data = analyticsService.getProductsByManufacture();
    return prepareChartData(data);
  }

  // Вспомогательный метод для упаковки в JSON
  private Map<String, Object> prepareChartData(Map<?, ?> data) {
    Map<String, Object> response = new HashMap<>();
    response.put("labels", data.keySet());
    response.put("values", data.values());
    return response;
  }
}