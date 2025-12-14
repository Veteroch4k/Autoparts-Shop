package com.popov314.autoparts.controller;

import com.popov314.autoparts.model.Supplier;
import com.popov314.autoparts.model.gui.User;
import com.popov314.autoparts.repository.gui.UserRepository;
import com.popov314.autoparts.service.MenuService;
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

  private UserRepository repository;
  private final MenuService menuService;


  @GetMapping("/users")
  public String users(Model model, Authentication authentication) {
    model.addAttribute("suppliers", repository.findAll());
    model.addAttribute("supplier", new Supplier());
    model.addAttribute("perms", menuService.getPagePermissions("/admin/users", authentication));
    return "/admin/users";
  }

  @PostMapping("/users/save")
  public String saveUsers(@ModelAttribute User user) {
    repository.save(user);
    return "redirect:/admin/users";
  }

  @DeleteMapping("/users/delete/{id}")
  public String deleteUsers(@PathVariable int id) {
    repository.deleteById(id);
    return "redirect:/admin/users";
  }

}
