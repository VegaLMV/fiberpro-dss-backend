package com.conexion.fiberpro.modules.nomina.controllers;

import com.conexion.fiberpro.modules.nomina.dtos.DetNominaCableGoDTO;
import com.conexion.fiberpro.modules.nomina.entities.DetNominaCableGo;
import com.conexion.fiberpro.modules.nomina.services.NominaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nomina")
public class NominaController {

    private final NominaService nominaService;

    @Autowired
    public NominaController(NominaService nominaService) {
        this.nominaService = nominaService;
    }

    @GetMapping("/registrar")
    public String mostrarPantallaNomina(
        @RequestParam(name = "db", required = false, defaultValue = "econo") String db,
        @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "nombres", required = false, defaultValue = "") String nombres,
        @RequestParam(name = "docIdent", required = false, defaultValue = "") String docIdent,
        @RequestParam(name = "estado", required = false, defaultValue = "") String estado,
        @RequestParam(name = "filial", required = false, defaultValue = "") String filial,
        Model model) {

        PageRequest pageRequest = PageRequest.of(page, 50, Sort.by("idDetNominaCableGo").descending());

        Page<DetNominaCableGo> paginaNominas = nominaService.readPaginatedNominasAvanzado(
            db, nombres, docIdent, estado, filial, pageRequest);

        model.addAttribute("paginaNominas", paginaNominas);
        model.addAttribute("dbActiva", db);
        model.addAttribute("nominaDTO", new DetNominaCableGoDTO());

        boolean hayFiltros = !nombres.isEmpty() || !docIdent.isEmpty() || !estado.isEmpty() || !filial.isEmpty();
        model.addAttribute("hayFiltros", hayFiltros);

        return "nomina";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute DetNominaCableGoDTO dto, @RequestParam("baseDatosDestino") String baseDatosDestino) {
        if (dto.getIdDetNominaCableGo() != null && dto.getIdDetNominaCableGo() > 0) {
            nominaService.updateNomina(dto.getIdDetNominaCableGo(), dto, baseDatosDestino);
        } else {
            nominaService.createNomina(dto, baseDatosDestino);
        }
        return "redirect:/nomina/registrar?db=" + baseDatosDestino + "&exito";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id, @RequestParam("db") String db) {
        nominaService.deleteNomina(id, db);
        return "redirect:/nomina/registrar?db=" + db + "&eliminado";
    }
}