package com.popov314.autoparts.controller;

import com.popov314.autoparts.model.Delivery;
import com.popov314.autoparts.model.Product;
import com.popov314.autoparts.model.ProductSupplier;
import com.popov314.autoparts.model.ProductSupplierId;
import com.popov314.autoparts.model.Supplier;
import com.popov314.autoparts.repository.DeliveryRepository;
import com.popov314.autoparts.repository.ProductRepository;
import com.popov314.autoparts.repository.ProductSupplierRepository;
import com.popov314.autoparts.repository.SupplierRepository;
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
@RequestMapping("/supply")
@RequiredArgsConstructor
class SupplyController {

  private final SupplierRepository supplierRepository;
  private final DeliveryRepository deliveryRepository;
  private final ProductSupplierRepository productSupplierRepository;
  private final ProductRepository productRepository;

  private final MenuService menuService;

  @GetMapping("/suppliers")
  public String suppliers(Model model, Authentication authentication) {
    model.addAttribute("suppliers", supplierRepository.findAll());
    model.addAttribute("supplier", new Supplier());
    model.addAttribute("perms", menuService.getPagePermissions("/supply/suppliers", authentication));
    return "/supply/suppliers";
  }

  @PostMapping("/suppliers/save")
  public String saveSupplier(@ModelAttribute Supplier supplier) {
    supplierRepository.save(supplier);
    return "redirect:/supply/suppliers";
  }

  @DeleteMapping("/suppliers/delete/{id}")
  @PreAuthorize("hasRole('DIRECTOR')")
  public String deleteSupplier(@PathVariable int id) {
    supplierRepository.deleteById(id);
    return "redirect:/supply/suppliers";
  }

  @GetMapping("/deliveries")
  public String deliveries(Model model, Authentication authentication) {
    model.addAttribute("deliveries", deliveryRepository.findAll());
    model.addAttribute("delivery", new Delivery());
    model.addAttribute("perms", menuService.getPagePermissions("/supply/deliveries", authentication));
    return "/supply/deliveries";
  }

  @PostMapping("/deliveries/save")
  public String saveDelivery(@ModelAttribute Delivery delivery) {
    deliveryRepository.save(delivery);
    return "redirect:/supply/deliveries";
  }

  @DeleteMapping("/deliveries/delete/{id}")
  @PreAuthorize("hasRole('DIRECTOR')")
  public String deleteDelivery(@PathVariable int id) {
    deliveryRepository.deleteById(id);
    return "redirect:/supply/deliveries";
  }

  @GetMapping("/psuppliers")
  public String productSuppliers(Model model, Authentication authentication) {
    model.addAttribute("productSuppliers", productSupplierRepository.findAll());

    ProductSupplier ps = new ProductSupplier();
    ps.setId(new ProductSupplierId());

    model.addAttribute("productSupplier", ps);
    model.addAttribute("perms", menuService.getPagePermissions("/supply/psuppliers", authentication));
    return "/supply/productSuppliers";

  }

  @PostMapping("/psuppliers/save")
  public String saveProductSuppliers(@ModelAttribute ProductSupplier ps) {

    Integer pId = ps.getId().getProductId();
    Integer sId = ps.getId().getSupplierId();

    Product productRef = productRepository.getReferenceById(pId);
    Supplier supplierRef = supplierRepository.getReferenceById(sId);

    ps.setProduct(productRef);
    ps.setSupplier(supplierRef);

    productSupplierRepository.save(ps);
    return "redirect:/supply/psuppliers";

  }

  @DeleteMapping("/psuppliers/delete/{pId}/{sId}")
  @PreAuthorize("hasRole('DIRECTOR')")
  public String deleteProductSuppliers(@PathVariable int pId, @PathVariable int sId) {
    productSupplierRepository.deleteById(new ProductSupplierId(pId, sId));
    return "redirect:/supply/psuppliers";
  }
}
