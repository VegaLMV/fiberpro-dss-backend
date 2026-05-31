package com.conexion.nominacrud.controller;

import jakarta.servlet.http.HttpSession;
import com.conexion.nominacrud.config.DBContextHolder;
import com.conexion.nominacrud.entity.DetNominaCableGo;
import com.conexion.nominacrud.repository.NominaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class NominaController {

    @Autowired
    private NominaRepository nominaRepository;

    // ==========================================
    // 1. PANTALLA REGISTRAR (Crear)
    // ==========================================
    @GetMapping("/")
    public String mostrarFormulario(Model model, HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) return "redirect:/login";
        
        model.addAttribute("nomina", new DetNominaCableGo());
        model.addAttribute("usuarioEnSesion", session.getAttribute("usuarioLogueado"));
        return "nomina";
    }

    @PostMapping("/guardar")
    public String guardarNomina(DetNominaCableGo nomina, @RequestParam("baseDatosDestino") String baseDatosDestino, HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) return "redirect:/login";

        DBContextHolder.setCurrentDb(baseDatosDestino);
        try {
            nominaRepository.save(nomina);
        } finally {
            DBContextHolder.clear();
        }
        return "redirect:/?exito";
    }

    // ==========================================
    // 2. PANTALLA CONSULTAR (Leer, Actualizar, Eliminar)
    // ==========================================
    @GetMapping("/consultar")
    public String mostrarConsulta(Model model, HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) return "redirect:/login";
        
        model.addAttribute("usuarioEnSesion", session.getAttribute("usuarioLogueado"));
        return "consultar";
    }

    @PostMapping("/buscar")
    public String buscarNomina(@RequestParam("baseDatosDestino") String baseDatosDestino, 
                               @RequestParam("idBuscar") Integer idBuscar, 
                               Model model, HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) return "redirect:/login";

        DBContextHolder.setCurrentDb(baseDatosDestino);
        try {
            // Buscamos el registro por su ID
            Optional<DetNominaCableGo> nominaOpt = nominaRepository.findById(idBuscar);
            
            if (nominaOpt.isPresent()) {
                model.addAttribute("nominaEncontrada", nominaOpt.get());
                model.addAttribute("bdSeleccionada", baseDatosDestino); // Guardamos la BD para saber dónde actualizar luego
            } else {
                model.addAttribute("error", "No se encontró ningún registro con el ID " + idBuscar + " en la base de datos seleccionada.");
            }
        } finally {
            DBContextHolder.clear();
        }
        
        model.addAttribute("usuarioEnSesion", session.getAttribute("usuarioLogueado"));
        return "consultar";
    }

    @PostMapping("/actualizar")
    public String actualizarNomina(@ModelAttribute("nominaEncontrada") DetNominaCableGo nomina, 
                                   @RequestParam("baseDatosDestino") String baseDatosDestino, 
                                   HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) return "redirect:/login";

        DBContextHolder.setCurrentDb(baseDatosDestino);
        try {
            // Al hacer save() con un ID que ya existe, Hibernate hace un UPDATE automático
            nominaRepository.save(nomina);
        } finally {
            DBContextHolder.clear();
        }
        return "redirect:/consultar?exitoUpdate";
    }

    @PostMapping("/eliminar")
    public String eliminarNomina(@RequestParam("idEliminar") Integer idEliminar, 
                                 @RequestParam("baseDatosDestino") String baseDatosDestino, 
                                 HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) return "redirect:/login";

        DBContextHolder.setCurrentDb(baseDatosDestino);
        try {
            nominaRepository.deleteById(idEliminar);
        } finally {
            DBContextHolder.clear();
        }
        return "redirect:/consultar?exitoDelete";
    }
}