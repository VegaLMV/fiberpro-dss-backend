package com.conexion.fiberpro.modules.clientes.controllers;

import com.conexion.fiberpro.modules.clientes.dtos.AbonadoDTO;
import com.conexion.fiberpro.modules.clientes.entities.Abonado;
import com.conexion.fiberpro.modules.clientes.services.AbonadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes/abonados")
public class AbonadoController {

    private final AbonadoService abonadoService;

    @Autowired
    public AbonadoController(AbonadoService abonadoService) {
        this.abonadoService = abonadoService;
    }

    @GetMapping("/registrar")
    public String mostrarPantallaAbonados(
        @RequestParam(name = "db", required = false, defaultValue = "econo") String db,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "tipoDoc", required = false, defaultValue = "") String tipoDoc,
        @RequestParam(name = "numDoc", required = false, defaultValue = "") String numDoc,
        @RequestParam(name = "nombres", required = false, defaultValue = "") String nombres,
        @RequestParam(name = "paterno", required = false, defaultValue = "") String paterno,
        @RequestParam(name = "materno", required = false, defaultValue = "") String materno,
        @RequestParam(name = "estado", required = false, defaultValue = "") String estado,
        Model model) {

        PageRequest pageRequest = PageRequest.of(page, 50, Sort.by("idAbonado").descending());

        Page<Abonado> paginaAbonados = abonadoService.readPaginatedAbonadosAvanzado(
            db, tipoDoc, numDoc, nombres, paterno, materno, estado, pageRequest);

        model.addAttribute("paginaAbonados", paginaAbonados);
        model.addAttribute("dbActiva", db);
        model.addAttribute("abonadoDTO", new AbonadoDTO());

        boolean hayFiltros = !tipoDoc.isEmpty() || !numDoc.isEmpty() || !nombres.isEmpty() || !paterno.isEmpty() || !materno.isEmpty() || !estado.isEmpty();
        model.addAttribute("hayFiltros", hayFiltros);

        return "abonados";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute AbonadoDTO dto, @RequestParam("baseDatosDestino") String baseDatosDestino) {
        if (dto.getIdAbonado() != null && dto.getIdAbonado() > 0) {
            abonadoService.updateAbonado(dto.getIdAbonado(), dto, baseDatosDestino);
        } else {
            abonadoService.createAbonado(dto, baseDatosDestino);
        }
        return "redirect:/clientes/abonados/registrar?db=" + baseDatosDestino + "&exito";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id, @RequestParam("db") String db) {
        abonadoService.deleteAbonado(id, db);
        return "redirect:/clientes/abonados/registrar?db=" + db + "&eliminado";
    }
}