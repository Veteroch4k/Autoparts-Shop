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

    String rawPassword = user.getPassword();

    String encodedPassword = passwordEncoder.encode(rawPassword);

    user.setPassword(encodedPassword);

    userRepository.save(user);
  }
}