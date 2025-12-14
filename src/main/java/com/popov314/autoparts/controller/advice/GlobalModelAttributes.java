package com.popov314.autoparts.controller.advice;

import com.popov314.autoparts.model.gui.MenuItem;
import com.popov314.autoparts.repository.gui.MenuRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttributes {

  @Autowired
  private MenuRepository menuRepository;

  // Эта штука сделает переменную "menuItems" доступной во ВСЕХ html шаблонах
  @ModelAttribute("menuItems")
  public List<MenuItem> populateMenu() {

    // 1. Узнаем, кто зашел
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    if (auth == null || !auth.isAuthenticated() || auth.getPrincipal().equals("anonymousUser")) {
      return List.of(); // Если не вошел - меню пустое
    }

    // 2. Берем его роль (обрезаем ROLE_ если нужно, или ищем как есть)
    // В БД у нас 'ROLE_SALES', а getAuthority() вернет тоже 'ROLE_SALES'
    String roleName = auth.getAuthorities().iterator().next().getAuthority();

    // 3. Достаем только то, что ему можно читать
    // Метод репозитория, который мы обсуждали выше
    // Но нам нужны только КОРНЕВЫЕ элементы (у которых parent_id = null),
    // а детей подтянет FetchType.LAZY или JOIN FETCH
    return menuRepository.findRootMenuByRole(roleName);
  }
}