package com.popov314.autoparts.controller;

import com.popov314.autoparts.model.gui.User;
import com.popov314.autoparts.repository.gui.UserRepository;
import com.popov314.autoparts.service.UserService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

  private final UserService userService;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @GetMapping("")
  public String index() {
    return "index";
  }

  @GetMapping("/guide")
  public String guide() {
    return "header/guide";
  }

  @GetMapping("/help")
  public String help() {
    return "header/help";
  }

  @GetMapping("/settings")
  public String settings() {
    return "header/settings";
  }

  @PostMapping("/settings/change-password")
  public String changePassword(
      @RequestParam("currentPassword") String currentPassword,
      @RequestParam("newPassword") String newPassword,
      @RequestParam("confirmPassword") String confirmPassword,
      Principal principal,
      RedirectAttributes redirectAttributes
  ) {
    // 1. Получаем текущего пользователя из базы
    String username = principal.getName();
    User user = userRepository.findByUserName(username)
        .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

    if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
      redirectAttributes.addFlashAttribute("errorMessage", "Неверный текущий пароль!");
      // Добавляем флаг, чтобы JS открыл вкладку безопасности
      redirectAttributes.addFlashAttribute("activeTab", "security");
      return "redirect:/settings";
    }

    // 3. Проверяем совпадение новых паролей
    if (!newPassword.equals(confirmPassword)) {
      redirectAttributes.addFlashAttribute("errorMessage", "Новые пароли не совпадают!");
      redirectAttributes.addFlashAttribute("activeTab", "security");
      return "redirect:/settings";
    }

    // 4. Вызываем ТВОЙ сервис (он сам захеширует и сохранит)
    // Передаем ID и "сырой" пароль
    userService.updatePassword(user.getId(), newPassword);

    redirectAttributes.addFlashAttribute("successMessage", "Пароль успешно изменен!");
    redirectAttributes.addFlashAttribute("activeTab", "security");

    return "redirect:/settings";
  }


}
