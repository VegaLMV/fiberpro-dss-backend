package com.conexion.fiberpro.modules.operaciones.controllers;

import com.conexion.fiberpro.modules.operaciones.dtos.OrdenServicioDTO;
import com.conexion.fiberpro.modules.operaciones.entities.OrdenServicio;
import com.conexion.fiberpro.modules.operaciones.services.OrdenServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/operaciones/ordenes")
public class OrdenServicioController {

    private final OrdenServicioService ordenServicioService;

    @Autowired
    public OrdenServicioController(OrdenServicioService ordenServicioService) {
        this.ordenServicioService = ordenServicioService;
    }

    @GetMapping("/registrar")
    public String mostrarPantallaOrdenes(
        @RequestParam(name = "db", required = false, defaultValue = "econo") String db,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "numeroOS", required = false) Integer numeroOS,
        @RequestParam(name = "idAbonado", required = false) Integer idAbonado,
        @RequestParam(name = "tipoOS", required = false, defaultValue = "") String tipoOS,
        @RequestParam(name = "estOS", required = false, defaultValue = "") String estOS,
        Model model) {

        PageRequest pageRequest = PageRequest.of(page, 50, Sort.by("idOS").descending());

        Page<OrdenServicio> paginaOrdenes = ordenServicioService.readPaginatedOrdenesAvanzado(
            db, numeroOS, idAbonado, tipoOS, estOS, pageRequest);

        model.addAttribute("paginaOrdenes", paginaOrdenes);
        model.addAttribute("dbActiva", db);
        model.addAttribute("ordenDTO", new OrdenServicioDTO());

        boolean hayFiltros = (numeroOS != null) || (idAbonado != null) || !tipoOS.isEmpty() || !estOS.isEmpty();
        model.addAttribute("hayFiltros", hayFiltros);

        return "operaciones";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute OrdenServicioDTO dto, @RequestParam("baseDatosDestino") String baseDatosDestino) {
        if (dto.getIdOS() != null && dto.getIdOS() > 0) {
            ordenServicioService.updateOrden(dto.getIdOS(), dto, baseDatosDestino);
        } else {
            ordenServicioService.registrarOrden(dto, baseDatosDestino);
        }
        return "redirect:/operaciones/ordenes/registrar?db=" + baseDatosDestino + "&exito";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id, @RequestParam("db") String db) {
        ordenServicioService.deleteOrden(id, db);
        return "redirect:/operaciones/ordenes/registrar?db=" + db + "&eliminado";
    }
}