package com.popov314.autoparts.controller;

import com.popov314.autoparts.model.gui.User;
import com.popov314.autoparts.repository.gui.UserRepository;
import com.popov314.autoparts.service.UserService;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

  private final UserRepository userRepository;
  private final UserService userService;

  // Страница входа
  @GetMapping("/login")
  public String loginPage() {
    return "/auth/login";
  }

  // Страница регистрации
  @GetMapping("/register")
  public String showRegisterForm(Model model) {
    model.addAttribute("user", new User());
    return "/auth/register";
  }

  // Обработка регистрации
  @PostMapping("/register")
  public String registerUser(@ModelAttribute("user") User user, Model model) {

    // не занят ли логин
    if (userRepository.findByUserName(user.getUserName()).isPresent()) {
      model.addAttribute("error", "Пользователь с таким логином уже есть");
      return "/auth/register";
    }

    userService.registerNewUser(user);

    return "redirect:/auth/login?registerSuccess=true";
  }

  // Страница смены пароля
  @GetMapping("/forgot-password")
  public String forgotPasswordPage() {
    return "/auth/password";
  }


  @PostMapping("/forgot-password")
  public String processForgotPassword(@RequestParam("email") String email,
      HttpSession session,
      Model model) {

    Optional<User> userOptional = userRepository.findByEmail(email);

    if (userOptional.isPresent()) {
      // Если нашли - сохраняем ID пользователя во временную сессию
      session.setAttribute("RESET_USER_ID", userOptional.get().getId());
      //перенаправляем на страницу ввода пароля
      return "redirect:/auth/reset-password";
    } else {
      // Если не нашли - ошибка
      model.addAttribute("error", "Пользователь с таким Email не найден");
      return "auth/password";
    }
  }

  @GetMapping("/reset-password")
  public String showResetPasswordForm(HttpSession session) {
    if (session.getAttribute("RESET_USER_ID") == null) {
      return "redirect:/auth/login";
    }
    return "auth/reset_password";
  }

  @PostMapping("/reset-password")
  public String processResetPassword(@RequestParam("password") String password,
      HttpSession session) {
    Integer userId = (Integer) session.getAttribute("RESET_USER_ID");

    if (userId != null) {
      // Вызываем метод сервиса для обновления пароля
      userService.updatePassword(userId, password);

      // Чистим сессию
      session.removeAttribute("RESET_USER_ID");
    }

    // Редирект на логин с сообщением об успехе
    return "redirect:/auth/login?resetSuccess=true";
  }



}
