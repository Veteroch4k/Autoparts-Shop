package com.popov314.autoparts;

import com.popov314.autoparts.service.DocumentService;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class DocumentServiceTest {

  private final JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
  private final DocumentService service = new DocumentService(jdbcTemplate);

  @Test
  void shouldAllowValidSelectQuery() {
    String sql = "SELECT * FROM products";

    assertDoesNotThrow(() -> service.executeQuery(sql));
  }

  @Test
  void shouldBlockDeleteQuery() {
    String sql = "DELETE FROM products";

    assertThrows(SecurityException.class, () -> {
      service.executeQuery(sql);
    });
  }

  @Test
  void shouldBlockDropWithComments() {
    String sql = "DROP /* скрытый */ TABLE products";
    assertThrows(SecurityException.class, () -> {
      service.executeQuery(sql);
    });
  }

  @Test
  void shouldBlockUpdateWithWeirdCasing() {
    String sql = "uPdAtE products SET price = 0";
    assertThrows(SecurityException.class, () -> service.executeQuery(sql));
  }

  @Test
  void shouldBlockChainedQueries() {
    String sql = "SELECT * FROM users; SELECT * FROM admins";
    assertThrows(SecurityException.class, () -> {
      service.executeQuery(sql);
    });
  }
}