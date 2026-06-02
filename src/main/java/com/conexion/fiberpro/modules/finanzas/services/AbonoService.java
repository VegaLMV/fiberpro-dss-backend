package com.conexion.fiberpro.modules.finanzas.services;

import com.conexion.fiberpro.config.DBContextHolder;
import com.conexion.fiberpro.modules.finanzas.dtos.AbonoDTO;
import com.conexion.fiberpro.modules.finanzas.entities.Abono;
import com.conexion.fiberpro.modules.finanzas.repositories.AbonoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AbonoService {

    private final AbonoRepository abonoRepository;

    @Autowired
    public AbonoService(AbonoRepository abonoRepository) {
        this.abonoRepository = abonoRepository;
    }

    public void registrarAbono(AbonoDTO dto, String baseDatosDestino) {
        try {
            DBContextHolder.setCurrentDb(baseDatosDestino);
            Abono abono = new Abono();
            mapearDtoAEntidad(dto, abono);
            // Si es un abono nuevo, establecemos la fecha actual
            if (abono.getIdAbono() == null) {
                abono.setFechaRegistro(LocalDateTime.now());
            }
            abonoRepository.save(abono);
        } finally {
            DBContextHolder.clear();
        }
    }

    public void updateAbono(Integer id, AbonoDTO dto, String baseDatosDestino) {
        try {
            DBContextHolder.setCurrentDb(baseDatosDestino);
            Optional<Abono> existente = abonoRepository.findById(id);
            if (existente.isPresent()) {
                Abono abono = existente.get();
                mapearDtoAEntidad(dto, abono);
                abono.setFechaUltMod(LocalDateTime.now());
                abonoRepository.save(abono);
            }
        } finally {
            DBContextHolder.clear();
        }
    }

    // === 1. BÚSQUEDA PAGINADA AVANZADA ===
    public Page<Abono> readPaginatedAbonosAvanzado(
        String db, Integer idAbonado, Integer periodo, String concepto, String estado, Pageable pageable) {
        try {
            DBContextHolder.setCurrentDb(db);
            return abonoRepository.busquedaAvanzada(idAbonado, periodo, concepto, estado, pageable);
        } finally {
            DBContextHolder.clear();
        }
    }

    // === 2. ELIMINACIÓN LÓGICA (Anulación de pago) ===
    public void deleteAbono(Integer id, String baseDatosDestino) {
        try {
            DBContextHolder.setCurrentDb(baseDatosDestino);
            Abono abono = abonoRepository.findById(id).orElse(null);
            if (abono != null) {
                abono.setEstado("ANU"); // Anulado
                abono.setFechaUltMod(LocalDateTime.now());
                abonoRepository.save(abono);
            }
        } finally {
            DBContextHolder.clear();
        }
    }

    private void mapearDtoAEntidad(AbonoDTO dto, Abono entidad) {
        entidad.setIdFilial(dto.getIdFilial() != null ? dto.getIdFilial() : 1);
        entidad.setIdAbonado(dto.getIdAbonado());
        entidad.setIdOS(dto.getIdOS());
        entidad.setCodigoOperacion(dto.getCodigoOperacion());
        entidad.setServicioOrigen(dto.getServicioOrigen());
        entidad.setFechaRegistro(dto.getFechaRegistro());
        entidad.setPeriodo(dto.getPeriodo());
        entidad.setConcepto(dto.getConcepto());
        entidad.setImporte(dto.getImporte());
        entidad.setClaseOperacionDestino(dto.getClaseOperacionDestino());
        entidad.setEstado(dto.getEstado());
        entidad.setIdUsuarioIng(dto.getIdUsuarioIng());
        entidad.setFechaIng(dto.getFechaIng());
        entidad.setIdUsuarioMod(dto.getIdUsuarioMod());
        entidad.setFechaUltMod(dto.getFechaUltMod());
        entidad.setIdUsuarioAprueba(dto.getIdUsuarioAprueba());
        entidad.setFechaAprueba(dto.getFechaAprueba());
        entidad.setIdAplicacionDescuento(dto.getIdAplicacionDescuento());
        entidad.setIndDescuento(dto.getIndDescuento());
        entidad.setCodMoneda(dto.getCodMoneda());
        entidad.setIdServicioOtros(dto.getIdServicioOtros());
        entidad.setIdAplicacionDescuentoCupon(dto.getIdAplicacionDescuentoCupon());
    }
}
