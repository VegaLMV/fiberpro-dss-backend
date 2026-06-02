package com.conexion.fiberpro.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/")
  public String raiz() {
    return "redirect:/auth/login";
  }

  @GetMapping("/layout")
  public String mostrarPanelAdministrativo(HttpSession session) {
    if (session.getAttribute("usuarioLogueado") == null) {
      return "redirect:/auth/login";
    }
    return "dashboard";
  }

}