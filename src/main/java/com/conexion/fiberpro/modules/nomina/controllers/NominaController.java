package com.conexion.fiberpro.modules.nomina.controllers;

import com.conexion.fiberpro.modules.nomina.dtos.DetNominaCableGoDTO;
import com.conexion.fiberpro.modules.nomina.services.NominaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controlador REST/MVC para los endpoints de Nómina.
 * Actúa como fachada recibiendo las peticiones y delegando la lógica.
 */
@Controller
@RequestMapping("/nomina")
public class NominaController {

    private final NominaService nominaService;

    /**
     * Inyección de dependencias por constructor.
     *
     * @param nominaService Servicio encargado de la lógica de negocio
     */
    @Autowired
    public NominaController(NominaService nominaService) {
        this.nominaService = nominaService;
    }

    /**
     * Endpoint para recibir y registrar un detalle de nómina.
     *
     * @param dto DTO poblado desde la petición del cliente.
     * @param baseDatosDestino Parámetro con el esquema dinámico al que se enrutará la transacción.
     * @return Una cadena con la redirección al recurso de éxito.
     */
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute DetNominaCableGoDTO dto, @RequestParam("baseDatosDestino") String baseDatosDestino) {
        nominaService.registrarDetalleNomina(dto, baseDatosDestino);
        return "redirect:/nomina/registrar?exito";
    }
}