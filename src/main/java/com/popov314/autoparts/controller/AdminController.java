package com.popov314.autoparts.controller;

import com.popov314.autoparts.model.gui.User;
import com.popov314.autoparts.repository.gui.UserRepository;
import com.popov314.autoparts.service.MenuService;
import com.popov314.autoparts.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@PreAuthorize("hasRole('DIRECTOR')")
public class AdminController {

  private final UserRepository userRepository;
  private final MenuService menuService;
  private final UserService userService;


  @GetMapping("/users")
  public String users(Model model, Authentication authentication) {
    model.addAttribute("users", userRepository.findAll());
    model.addAttribute("user", new User());
    model.addAttribute("perms", menuService.getPagePermissions("/admin/users", authentication));
    return "/admin/users";
  }

  @PostMapping("/users/save")
  public String saveUsers(@ModelAttribute User user) {

    if(user.getId() == null) userService.registerNewUser(user);
    else userRepository.save(user);

    return "redirect:/admin/users";
  }

  @DeleteMapping("/users/delete/{id}")
  public String deleteUsers(@PathVariable int id) {
    userRepository.deleteById(id);
    return "redirect:/admin/users";
  }

}
