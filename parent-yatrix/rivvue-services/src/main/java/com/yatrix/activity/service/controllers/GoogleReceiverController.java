package com.yatrix.activity.service.controllers;

import com.yatrix.activity.store.mongo.domain.Role;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/google")
public class GoogleReceiverController {

  @ModelAttribute("allRoles")
  public Role[] getAllRoles() {
    return new Role[] { Role.ROLE_USER };
  }

  @RequestMapping(
      value = "/Callback")
  public String delete(String code) {
    System.out.println("String: " + code);
    return "google/connect";
  }
}
