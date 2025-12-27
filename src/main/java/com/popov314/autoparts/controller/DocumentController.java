package com.popov314.autoparts.controller;

import com.popov314.autoparts.service.DocumentService;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/documents")
@RequiredArgsConstructor
@PreAuthorize("hasRole('DIRECTOR') or hasRole ('SALES')" )
public class DocumentController {

  private final DocumentService documentService;


  @GetMapping("")
  public String showPage(Model model) {
    return "header/document";
  }
  @PostMapping("/manual")
  @PreAuthorize("hasRole('DIRECTOR')")
  public String executeManual(@RequestParam("sqlQuery") String sql, Model model) {
    performQuery(sql, model);

    model.addAttribute("currentReportId", null);

    return "header/document";
  }

  @PostMapping("/preset")
  @PreAuthorize("hasAnyRole('DIRECTOR', 'SALES')")
  public String executePreset(@RequestParam("reportId") Integer reportId, Model model) {
    // Получаем SQL по ID
    String sql = documentService.getPredefinedQuery(reportId);

    // Выполняем
    performQuery(sql, model);

    model.addAttribute("currentReportId", reportId);

    return "header/document";
  }

  private void performQuery(String sql, Model model) {
    model.addAttribute("sqlQuery", sql);
    try {
      var result = documentService.executeQuery(sql);
      model.addAttribute("queryResult", result);
      if (!result.isEmpty()) {
        model.addAttribute("columns", result.getFirst().keySet());
      } else {
        model.addAttribute("message", "Запрос вернул пустой результат.");
      }
    } catch (Exception e) {
      model.addAttribute("errorMessage", "Ошибка выполнения: " + e.getMessage());
    }
  }


  @PostMapping("/export")
  public void exportToCsv(
      @RequestParam(value = "sqlQuery", required = false) String userSql,
      @RequestParam(value = "reportId", required = false) Integer reportId,
      HttpServletResponse response,
      Authentication authentication  ) throws IOException {

    String sqlQuery;

    if (reportId != null) {
      sqlQuery = documentService.getPredefinedQuery(reportId);
    }
    else {
      // Проверяем роль
      boolean isDirector = authentication.getAuthorities().stream()
          .anyMatch(r -> Objects.equals(r.getAuthority(), "ROLE_DIRECTOR"));

      if (!isDirector) {
        response.sendError(403, "Только директор может экспортировать произвольные запросы");
        return;
      }

      // Если директор — доверяем
      sqlQuery = userSql;
    }
    // Настраиваем заголовки ответа
    response.setContentType("text/csv; charset=UTF-8");
    String filename = "report_" + System.currentTimeMillis() + ".csv";
    response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

    try (PrintWriter writer = response.getWriter()) {

      writer.write('\ufeff');

      // Проверка на пустой запрос
      if (sqlQuery == null || sqlQuery.isBlank()) {
        writer.println("Ошибка: SQL запрос пуст");
        return;
      }

      try {
        // Выполняем запрос
        List<Map<String, Object>> result = documentService.executeQuery(sqlQuery);

        if (result.isEmpty()) {
          writer.println("Запрос вернул пустой результат (нет данных)");
          return;
        }

        // Берем ключи из первой строки результата
        Set<String> columns = result.getFirst().keySet();
        writer.println(String.join(";", columns));

        // Записываем СТРОКИ
        for (Map<String, Object> row : result) {
          String line = columns.stream()
              .map(col -> {
                Object val = row.get(col);
                if (val == null)
                  return ""; // Пустая ячейка

                return val.toString()
                    .replace(";", ",")
                    .replace("\n", " ")
                    .replace("\r", "");
              })
              .collect(Collectors.joining(";"));

          writer.println(line);
        }

      } catch (Exception e) {
        writer.println("ОШИБКА ВЫПОЛНЕНИЯ SQL:");
        writer.println(e.getMessage());
      }
    }

  }
}
