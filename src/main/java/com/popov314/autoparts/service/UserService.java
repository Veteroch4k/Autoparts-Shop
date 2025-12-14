package com.popov314.autoparts.service;

import com.popov314.autoparts.model.gui.User;
import com.popov314.autoparts.repository.gui.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder; // <--- Внедряем

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
    this.passwordEncoder = new BCryptPasswordEncoder(8);
  }

  // Метод регистрации/сохранения
  public void registerNewUser(User user) {

    user.setRole("ROLE_GUEST");
    user.setEnabled(true);

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    // 4. Сохраняем
    userRepository.save(user);
  }

  // Метод для обновления пароля
  public void updatePassword(Integer userId, String newPassword) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));

    // Хешируем новый пароль
    user.setPassword(passwordEncoder.encode(newPassword));

    userRepository.save(user);
  }
}