package com.conexion.fiberpro.modules.nomina.services;

import com.conexion.fiberpro.config.DBContextHolder;
import com.conexion.fiberpro.modules.nomina.dtos.DetNominaCableGoDTO;
import com.conexion.fiberpro.modules.nomina.entities.DetNominaCableGo;
import com.conexion.fiberpro.modules.nomina.repositories.NominaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NominaService {

    private final NominaRepository nominaRepository;

    @Autowired
    public NominaService(NominaRepository nominaRepository) {
        this.nominaRepository = nominaRepository;
    }

    public void createNomina(DetNominaCableGoDTO dto, String baseDatosDestino) {
        try {
            DBContextHolder.setCurrentDb(baseDatosDestino);
            DetNominaCableGo entidad = new DetNominaCableGo();
            mapearDtoAEntidad(dto, entidad);
            nominaRepository.save(entidad);
        } finally {
            DBContextHolder.clear();
        }
    }

    public void updateNomina(Integer id, DetNominaCableGoDTO dto, String baseDatosDestino) {
        try {
            DBContextHolder.setCurrentDb(baseDatosDestino);
            Optional<DetNominaCableGo> existente = nominaRepository.findById(id);
            if (existente.isPresent()) {
                DetNominaCableGo entidad = existente.get();
                mapearDtoAEntidad(dto, entidad);
                nominaRepository.save(entidad);
            }
        } finally {
            DBContextHolder.clear();
        }
    }

    // === 1. BÚSQUEDA PAGINADA AVANZADA ===
    public Page<DetNominaCableGo> readPaginatedNominasAvanzado(
        String db, String nombres, String docIdent, String estado, String filial, Pageable pageable) {
        try {
            DBContextHolder.setCurrentDb(db);
            return nominaRepository.busquedaAvanzada(nombres, docIdent, estado, filial, pageable);
        } finally {
            DBContextHolder.clear();
        }
    }

    // === 2. ELIMINACIÓN LÓGICA (Soft Delete) ===
    public void deleteNomina(Integer id, String baseDatosDestino) {
        try {
            DBContextHolder.setCurrentDb(baseDatosDestino);
            DetNominaCableGo nomina = nominaRepository.findById(id).orElse(null);
            if (nomina != null) {
                nomina.setEstadoServicio("ELIMINADO");
                nominaRepository.save(nomina);
            }
        } finally {
            DBContextHolder.clear();
        }
    }

    private void mapearDtoAEntidad(DetNominaCableGoDTO dto, DetNominaCableGo entidad) {
        entidad.setIdCabNominaCableGo(dto.getIdCabNominaCableGo());
        entidad.setOrigen(dto.getOrigen());
        entidad.setAbonado(dto.getAbonado());
        entidad.setEstadoServicio(dto.getEstadoServicio());
        entidad.setNombres(dto.getNombres());
        entidad.setDocIdent(dto.getDocIdent());
        entidad.setCorreo(dto.getCorreo());
        entidad.setDiaFacturacion(dto.getDiaFacturacion());
        entidad.setAccesos(dto.getAccesos());
        entidad.setGrabacion(dto.getGrabacion());
        entidad.setPaquetePrincipal(dto.getPaquetePrincipal());
        entidad.setPaquetesADD(dto.getPaquetesADD());
        entidad.setTotalMensual(dto.getTotalMensual());
        entidad.setFechaSuscripcion(dto.getFechaSuscripcion());
        entidad.setFechaCancelacion(dto.getFechaCancelacion());
        entidad.setMotivoCancelacion(dto.getMotivoCancelacion());
        entidad.setCelular(dto.getCelular());
        entidad.setPerfilAsignado(dto.getPerfilAsignado());
        entidad.setAfiliaciones(dto.getAfiliaciones());
        entidad.setFechaAfiliacion(dto.getFechaAfiliacion());
        entidad.setFechaUltPago(dto.getFechaUltPago());
        entidad.setVendedor(dto.getVendedor());
        entidad.setFilial(dto.getFilial());
        entidad.setOrigenVenta(dto.getOrigenVenta());
        entidad.setUsuario(dto.getUsuario());
        entidad.setCategoria(dto.getCategoria());
        entidad.setFechaInstalacion(dto.getFechaInstalacion());
        entidad.setTarifaVigente(dto.getTarifaVigente());
        entidad.setPrecioTarifa(dto.getPrecioTarifa());
        entidad.setCanalAtencion(dto.getCanalAtencion());
        entidad.setTarifaPlanInicialContrato(dto.getTarifaPlanInicialContrato());
    }
}
