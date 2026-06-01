package com.conexion.fiberpro.modules.clientes.controllers;

import com.conexion.fiberpro.modules.clientes.dtos.AbonadoDTO;
import com.conexion.fiberpro.modules.clientes.services.AbonadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controlador REST/MVC para los endpoints de Abonado.
 * Actúa como fachada recibiendo las peticiones, validando contratos (DTOs)
 * y delegando la lógica central a la capa de servicios.
 */
@Controller
@RequestMapping("/clientes/abonados")
public class AbonadoController {

    private final AbonadoService abonadoService;

    /**
     * Inyección de dependencias por constructor.
     *
     * @param abonadoService Servicio encargado de la lógica de negocio de Abonado
     */
    @Autowired
    public AbonadoController(AbonadoService abonadoService) {
        this.abonadoService = abonadoService;
    }

    /**
     * Endpoint para recibir y registrar un abonado.
     *
     * @param dto DTO poblado desde la petición del cliente.
     * @param baseDatosDestino Parámetro con el esquema dinámico al que se enrutará la transacción.
     * @return Una cadena con la redirección al recurso de éxito.
     */
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute AbonadoDTO dto, @RequestParam("baseDatosDestino") String baseDatosDestino) {
        abonadoService.registrarAbonado(dto, baseDatosDestino);
        return "redirect:/clientes/abonados/registrar?exito";
    }
}
