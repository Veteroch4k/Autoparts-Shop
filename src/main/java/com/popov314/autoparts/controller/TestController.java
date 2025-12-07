package com.popov314.autoparts.controller;

import com.popov314.autoparts.model.reference_tables.City;
import com.popov314.autoparts.repository.ref_tables.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TestController {

  @Autowired
  CityRepository repository;

  @GetMapping("")
  public City getCity() {
    System.out.println(repository.findById(1).get());
    return repository.findById(1).get();
  }

}
