package com.conexion.fiberpro.modules.finanzas.controllers;

import com.conexion.fiberpro.modules.finanzas.dtos.AbonoDTO;
import com.conexion.fiberpro.modules.finanzas.services.AbonoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/finanzas/abonos")
public class AbonoController {

    private final AbonoService abonoService;

    @Autowired
    public AbonoController(AbonoService abonoService) {
        this.abonoService = abonoService;
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute AbonoDTO dto, @RequestParam("baseDatosDestino") String baseDatosDestino) {
        // Delegamos absolutamente toda la lógica de negocio y transformación al Service
        abonoService.registrarAbono(dto, baseDatosDestino);
        
        return "redirect:/finanzas/abonos/registrar?exito";
    }
}