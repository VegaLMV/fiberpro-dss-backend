package com.conexion.fiberpro.modules.finanzas.controllers;

import com.conexion.fiberpro.modules.finanzas.dtos.AbonoDTO;
import com.conexion.fiberpro.modules.finanzas.entities.Abono;
import com.conexion.fiberpro.modules.finanzas.services.AbonoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/finanzas/abonos")
public class AbonoController {

    private final AbonoService abonoService;

    @Autowired
    public AbonoController(AbonoService abonoService) {
        this.abonoService = abonoService;
    }

    @GetMapping("/registrar")
    public String mostrarPantallaAbonos(
        @RequestParam(name = "db", required = false, defaultValue = "econo") String db,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "idAbonado", required = false) Integer idAbonado,
        @RequestParam(name = "periodo", required = false) Integer periodo,
        @RequestParam(name = "concepto", required = false, defaultValue = "") String concepto,
        @RequestParam(name = "estado", required = false, defaultValue = "") String estado,
        Model model) {

        PageRequest pageRequest = PageRequest.of(page, 50, Sort.by("idAbono").descending());

        Page<Abono> paginaAbonos = abonoService.readPaginatedAbonosAvanzado(
            db, idAbonado, periodo, concepto, estado, pageRequest);

        model.addAttribute("paginaAbonos", paginaAbonos);
        model.addAttribute("dbActiva", db);
        model.addAttribute("abonoDTO", new AbonoDTO());

        boolean hayFiltros = (idAbonado != null) || (periodo != null) || !concepto.isEmpty() || !estado.isEmpty();
        model.addAttribute("hayFiltros", hayFiltros);

        return "abonos";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute AbonoDTO dto, @RequestParam("baseDatosDestino") String baseDatosDestino) {
        if (dto.getIdAbono() != null && dto.getIdAbono() > 0) {
            abonoService.updateAbono(dto.getIdAbono(), dto, baseDatosDestino);
        } else {
            abonoService.registrarAbono(dto, baseDatosDestino);
        }
        return "redirect:/finanzas/abonos/registrar?db=" + baseDatosDestino + "&exito";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id, @RequestParam("db") String db) {
        abonoService.deleteAbono(id, db);
        return "redirect:/finanzas/abonos/registrar?db=" + db + "&eliminado";
    }
}