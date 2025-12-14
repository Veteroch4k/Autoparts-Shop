package com.popov314.autoparts.controller;

import com.popov314.autoparts.model.Client;
import com.popov314.autoparts.model.DefectiveProduct;
import com.popov314.autoparts.model.Order;
import com.popov314.autoparts.model.Sale;
import com.popov314.autoparts.model.gui.MenuDto;
import com.popov314.autoparts.repository.ClientRepository;
import com.popov314.autoparts.repository.DefectiveProductRepository;
import com.popov314.autoparts.repository.OrderRepository;
import com.popov314.autoparts.repository.SaleRepository;
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
  private final SaleRepository saleRepository;
  private final OrderRepository orderRepository;
  private final DefectiveProductRepository defectiveProductRepository;
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
  public String sales(Model model, Authentication authentication) {
    model.addAttribute("sales", saleRepository.findAll());
    model.addAttribute("sale", new Sale());
    model.addAttribute("perms", menuService.getPagePermissions("/trade/sales", authentication));
    return "/trade/sales";
  }

  @PostMapping("/sales/save")
  public String saveSale(@ModelAttribute Sale sale) {
    saleRepository.save(sale);
    return "redirect:/trade/sales";
  }

  @DeleteMapping("/sales/delete/{id}")
  @PreAuthorize("hasRole('DIRECTOR')")
  public String deleteSale(@PathVariable int id) {
    saleRepository.deleteById(id);
    return "redirect:/trade/sales";
  }




  @GetMapping("/orders")
  public String orders(Model model, Authentication authentication) {
    model.addAttribute("orders", orderRepository.findAll());
    model.addAttribute("order", new Order());
    model.addAttribute("perms", menuService.getPagePermissions("/trade/orders", authentication));
    return "/trade/orders";
  }

  @PostMapping("/orders/save")
  public String saveOrder(@ModelAttribute Order order) {
    orderRepository.save(order);
    return "redirect:/trade/orders";
  }

  @DeleteMapping("/orders/delete/{id}")
  @PreAuthorize("hasRole('DIRECTOR')")
  public String deleteOrder(@PathVariable int id) {
    orderRepository.deleteById(id);
    return "redirect:/trade/orders";
  }




  @GetMapping("/defective")
  public String defective(Model model, Authentication authentication) {
    model.addAttribute("defectives", defectiveProductRepository.findAll());
    model.addAttribute("defectiveProduct", new DefectiveProduct());
    model.addAttribute("perms", menuService.getPagePermissions("/trade/defective", authentication));
    return "/trade/defectives";
  }

  @PostMapping("/defective/save")
  public String saveDefective(@ModelAttribute DefectiveProduct defectiveProduct) {
    defectiveProductRepository.save(defectiveProduct);
    return "redirect:/trade/defectives";
  }

  @DeleteMapping("/defective/delete/{id}")
  @PreAuthorize("hasRole('DIRECTOR')")
  public String deleteDefective(@PathVariable int id) {
    defectiveProductRepository.deleteById(id);
    return "redirect:/trade/defectives";
  }


}
