package com.popov314.autoparts.controller.analytics;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/analytics")
@PreAuthorize("hasRole('DIRECTOR') or hasRole ('SALES')" )
public class AnalyticsController {

  @GetMapping("")
  public String analytics() {
    return "header/analytics";
  }

}
