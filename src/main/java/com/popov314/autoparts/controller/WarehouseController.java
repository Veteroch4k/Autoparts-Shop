package com.popov314.autoparts.controller;


import com.popov314.autoparts.model.CellInventory;
import com.popov314.autoparts.model.Manufacture;
import com.popov314.autoparts.model.Product;
import com.popov314.autoparts.model.StorageCell;
import com.popov314.autoparts.repository.CellInventoryRepository;
import com.popov314.autoparts.repository.ManufactureRepository;
import com.popov314.autoparts.repository.ProductRepository;
import com.popov314.autoparts.repository.StorageCellRepository;
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
@RequestMapping("/store")
@RequiredArgsConstructor
class WarehouseController {

  private final ProductRepository productRepository;
  private final ManufactureRepository manufactureRepository;
  private final StorageCellRepository storageCellRepository;
  private final CellInventoryRepository cellInventoryRepository;

  private final MenuService menuService;

  @GetMapping("/products")
  public String products(Model model, Authentication authentication) {
    model.addAttribute("products", productRepository.findAll());
    model.addAttribute("product", new Product());
    model.addAttribute("perms", menuService.getPagePermissions("/store/products", authentication));
    return "/store/products";
  }

  @PostMapping("/products/save")
  public String saveProduct(@ModelAttribute Product product) {
    productRepository.save(product);
    return "redirect:/store/products";
  }

  @DeleteMapping("/products/delete/{id}")
  @PreAuthorize("hasRole('DIRECTOR')")
  public String deleteProduct(@PathVariable int id) {
    productRepository.deleteById(id);
    return "redirect:/store/products";
  }

  @GetMapping("/manufactures")
  public String manufactures(Model model, Authentication authentication) {
    model.addAttribute("manufactures", manufactureRepository.findAll());
    model.addAttribute("manufacture", new Manufacture());
    model.addAttribute("perms", menuService.getPagePermissions("/store/manufactures", authentication));
    return "/store/manufactures";
  }

  @PostMapping("/manufactures/save")
  public String saveManufacture(@ModelAttribute Manufacture manufacture) {
    manufactureRepository.save(manufacture);
    return "redirect:/store/manufactures";
  }

  @DeleteMapping("/manufactures/delete/{id}")
  @PreAuthorize("hasRole('DIRECTOR')")
  public String deleteManufacture(@PathVariable int id) {
    manufactureRepository.deleteById(id);
    return "redirect:/store/manufactures";
  }

  @GetMapping("/cells")
  public String cells(Model model, Authentication authentication) {
    model.addAttribute("cells", storageCellRepository.findAll());
    model.addAttribute("cell", new StorageCell());
    model.addAttribute("perms", menuService.getPagePermissions("/store/cells", authentication));
    return "/store/cells";
  }

  @PostMapping("/cells/save")
  public String saveCell(@ModelAttribute StorageCell cell) {
    storageCellRepository.save(cell);
    return "redirect:/store/cells";
  }

  @DeleteMapping("/cells/delete/{id}")
  @PreAuthorize("hasRole('DIRECTOR')")
  public String deleteCell(@PathVariable int id) {
    storageCellRepository.deleteById(id);
    return "redirect:/store/cells";
  }

  @GetMapping("/inventory")
  public String inventory(Model model, Authentication authentication) {
    model.addAttribute("inventory", cellInventoryRepository.findAll());
    model.addAttribute("cellInventory", new CellInventory());
    model.addAttribute("perms", menuService.getPagePermissions("/store/inventory", authentication));
    return "/store/inventory";
  }

  @PostMapping("/inventory/save")
  public String saveInventory(@ModelAttribute CellInventory cellInventory) {
    cellInventoryRepository.save(cellInventory);
    return "redirect:/store/inventory";
  }

  @DeleteMapping("/inventory/delete/{id}")
  @PreAuthorize("hasRole('DIRECTOR')")
  public String deleteInventory(@PathVariable int id) {
    cellInventoryRepository.deleteById(id);
    return "redirect:/store/inventory";
  }
}
