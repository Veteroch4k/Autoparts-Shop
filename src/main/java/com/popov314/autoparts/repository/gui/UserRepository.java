package com.popov314.autoparts.repository.gui;

import com.popov314.autoparts.model.gui.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByEmail(String email);
  Optional<User> findByUserName(String userName);

}
