package com.conexion.fiberpro.modules.operaciones.services;

import com.conexion.fiberpro.config.DBContextHolder;
import com.conexion.fiberpro.modules.operaciones.dtos.OrdenServicioDTO;
import com.conexion.fiberpro.modules.operaciones.entities.OrdenServicio;
import com.conexion.fiberpro.modules.operaciones.repositories.OrdenServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrdenServicioService {

    private final OrdenServicioRepository ordenServicioRepository;

    @Autowired
    public OrdenServicioService(OrdenServicioRepository ordenServicioRepository) {
        this.ordenServicioRepository = ordenServicioRepository;
    }

    public void registrarOrden(OrdenServicioDTO dto, String db) {
        try {
            DBContextHolder.setCurrentDb(db);
            OrdenServicio orden = new OrdenServicio();
            mapearDtoAEntidad(dto, orden);
            orden.setFechaRecepcion(LocalDateTime.now());
            ordenServicioRepository.save(orden);
        } finally {
            DBContextHolder.clear();
        }
    }

    public void updateOrden(Integer id, OrdenServicioDTO dto, String db) {
        try {
            DBContextHolder.setCurrentDb(db);
            Optional<OrdenServicio> existente = ordenServicioRepository.findById(id);
            if (existente.isPresent()) {
                OrdenServicio orden = existente.get();
                mapearDtoAEntidad(dto, orden);
                orden.setFechaUltMod(LocalDateTime.now());
                ordenServicioRepository.save(orden);
            }
        } finally {
            DBContextHolder.clear();
        }
    }

    public Page<OrdenServicio> readPaginatedOrdenesAvanzado(
        String db, Integer numeroOS, Integer idAbonado, String tipoOS, String estOS, Pageable pageable) {
        try {
            DBContextHolder.setCurrentDb(db);
            return ordenServicioRepository.busquedaAvanzada(numeroOS, idAbonado, tipoOS, estOS, pageable);
        } finally {
            DBContextHolder.clear();
        }
    }

    public void deleteOrden(Integer id, String db) {
        try {
            DBContextHolder.setCurrentDb(db);
            OrdenServicio orden = ordenServicioRepository.findById(id).orElse(null);
            if (orden != null) {
                orden.setEstOS("ANU"); // Anulación Lógica
                orden.setFechaUltMod(LocalDateTime.now());
                ordenServicioRepository.save(orden);
            }
        } finally {
            DBContextHolder.clear();
        }
    }

    private void mapearDtoAEntidad(OrdenServicioDTO dto, OrdenServicio entidad) {
        entidad.setIdOS(dto.getIdOS());
        entidad.setIdFilial(dto.getIdFilial() != null ? dto.getIdFilial() : 1);
        entidad.setNumeroOS(dto.getNumeroOS());
        entidad.setIdAbonado(dto.getIdAbonado());
        entidad.setTipoOS(dto.getTipoOS());
        entidad.setEstOS(dto.getEstOS());
        entidad.setIdTecnico(dto.getIdTecnico());
        entidad.setClaseServicio(dto.getClaseServicio());
        entidad.setObservaciones(dto.getObservaciones());
    }
}