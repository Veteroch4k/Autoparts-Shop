package com.popov314.autoparts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

  @GetMapping("")
  public String index() {
    return "index";
  }

  @GetMapping("/guide")
  public String guide() {
    return "header/guide";
  }

  @GetMapping("/settings")
  public String settings() {
    return "header/settings";
  }


}
