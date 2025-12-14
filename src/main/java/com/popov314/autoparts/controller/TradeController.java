package com.popov314.autoparts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trade")
public class TradeController {


  @GetMapping("/clients")
  public String clients() {
    return "clients";
  }

  @GetMapping("/sales")
  public String clients() {
    return "sales";
  }

  @GetMapping("/orders")
  public String clients() {
    return "orders";
  }

  @GetMapping("/defective")
  public String clients() {
    return "defective";
  }

}
