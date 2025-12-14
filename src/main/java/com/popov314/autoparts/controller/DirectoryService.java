package com.popov314.autoparts.controller;

import com.popov314.autoparts.model.reference_tables.City;
import com.popov314.autoparts.model.reference_tables.Profession;
import com.popov314.autoparts.model.reference_tables.Qualification;
import com.popov314.autoparts.model.reference_tables.Specialty;
import com.popov314.autoparts.model.reference_tables.Street;
import com.popov314.autoparts.model.reference_tables.WorkPlace;
import com.popov314.autoparts.repository.ref_tables.CityRepository;
import com.popov314.autoparts.repository.ref_tables.ProfessionRepository;
import com.popov314.autoparts.repository.ref_tables.QualificationRepository;
import com.popov314.autoparts.repository.ref_tables.SpecialtyRepository;
import com.popov314.autoparts.repository.ref_tables.StreetRepository;
import com.popov314.autoparts.repository.ref_tables.WorkPlaceRepository;
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
@RequestMapping("/ref")
@RequiredArgsConstructor
class DirectoryService {

  private final CityRepository cityRepository;
  private final StreetRepository streetRepository;

  private final MenuService menuService;

  @GetMapping("/cities")
  public String cities(Model model, Authentication authentication) {
    model.addAttribute("cities", cityRepository.findAll());
    model.addAttribute("city", new City());
    model.addAttribute("perms", menuService.getPagePermissions("/ref/cities", authentication));
    return "/ref/cities";
  }

  @PostMapping("/cities/save")
  public String saveCity(@ModelAttribute City city) {
    cityRepository.save(city);
    return "redirect:/ref/cities";
  }

  @DeleteMapping("/cities/delete/{id}")
  @PreAuthorize("hasRole('DIRECTOR')")
  public String deleteCity(@PathVariable int id) {
    cityRepository.deleteById(id);
    return "redirect:/ref/cities";
  }

  @GetMapping("/streets")
  public String streets(Model model, Authentication authentication) {
    model.addAttribute("streets", streetRepository.findAll());
    model.addAttribute("street", new Street());
    model.addAttribute("perms", menuService.getPagePermissions("/ref/streets", authentication));
    return "/ref/streets";
  }

  @PostMapping("/streets/save")
  public String saveStreet(@ModelAttribute Street street) {
    streetRepository.save(street);
    return "redirect:/ref/streets";
  }

  @DeleteMapping("/streets/delete/{id}")
  @PreAuthorize("hasRole('DIRECTOR')")
  public String deleteStreet(@PathVariable int id) {
    streetRepository.deleteById(id);
    return "redirect:/ref/streets";
  }

}
