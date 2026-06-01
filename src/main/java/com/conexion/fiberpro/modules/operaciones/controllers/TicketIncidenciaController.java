package com.conexion.fiberpro.modules.operaciones.controllers;

import com.conexion.fiberpro.modules.operaciones.dtos.TicketIncidenciaDTO;
import com.conexion.fiberpro.modules.operaciones.services.TicketIncidenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/operaciones/incidencias")
public class TicketIncidenciaController {

    private final TicketIncidenciaService ticketIncidenciaService;

    @Autowired
    public TicketIncidenciaController(TicketIncidenciaService ticketIncidenciaService) {
        this.ticketIncidenciaService = ticketIncidenciaService;
    }
    @GetMapping("/registrar")
    public String mostrarPantallaOperaciones() {
        // Esto le dice a Spring Boot: "Busca el archivo incidencias.html en la carpeta templates y muéstralo"
        return "incidencias";
    }
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute TicketIncidenciaDTO dto, @RequestParam("baseDatosDestino") String baseDatosDestino) {
        
        // El Controller se mantiene limpio y delega el DTO al Service
        ticketIncidenciaService.registrarIncidencia(dto, baseDatosDestino);
        
        return "redirect:/operaciones/incidencias/registrar?exito";
    }
}
