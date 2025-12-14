package com.popov314.autoparts.controller;

import com.popov314.autoparts.model.Client;
import com.popov314.autoparts.model.gui.MenuDto;
import com.popov314.autoparts.repository.ClientRepository;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/trade")
@RequiredArgsConstructor
public class TradeController {

  private final ClientRepository repository;
  private final MenuService menuService;


  @GetMapping("/clients")
  public String clients(Model model, Authentication authentication) {
    model.addAttribute("clients", repository.findAll());
    model.addAttribute("client", new Client());

    // Передаем URL этой страницы ("/trade/clients") и данные юзера
    MenuDto pagePerms = menuService.getPagePermissions("/trade/clients", authentication);

    // 3. Кладем объект прав в модель
    model.addAttribute("perms", pagePerms);

    return "/trade/clients";
  }

  @PostMapping("/clients/save")
  public String save(@ModelAttribute Client client) {
    repository.save(client);
    return "redirect:/trade/clients";
  }

  @DeleteMapping("/clients/delete/{id}")
  @PreAuthorize("hasRole('DIRECTOR')")
  public String delete(@PathVariable int id) {

    repository.deleteById(id);

    return "redirect:/trade/clients";

  }

  @GetMapping("/sales")
  public String sales() {
    return "sales";
  }

  @GetMapping("/orders")
  public String orders() {
    return "orders";
  }

  @GetMapping("/defective")
  public String defective() {
    return "defective";
  }

}
