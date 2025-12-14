package com.popov314.autoparts.repository.gui;

import com.popov314.autoparts.model.gui.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
