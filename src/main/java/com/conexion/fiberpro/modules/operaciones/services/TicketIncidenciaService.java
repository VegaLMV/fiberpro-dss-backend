package com.conexion.fiberpro.modules.operaciones.services;

import com.conexion.fiberpro.config.DBContextHolder;
import com.conexion.fiberpro.modules.operaciones.dtos.TicketIncidenciaDTO;
import com.conexion.fiberpro.modules.operaciones.entities.TicketIncidencia;
import com.conexion.fiberpro.modules.operaciones.repositories.TicketIncidenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketIncidenciaService {

    private final TicketIncidenciaRepository ticketIncidenciaRepository;

    @Autowired
    public TicketIncidenciaService(TicketIncidenciaRepository ticketIncidenciaRepository) {
        this.ticketIncidenciaRepository = ticketIncidenciaRepository;
    }

    public void registrarIncidencia(TicketIncidenciaDTO dto, String baseDatosDestino) {
        try {
            // 1. Enrutamiento dinámico a la base de datos solicitada
            DBContextHolder.setCurrentDb(baseDatosDestino);

            // 2. Mapeo manual del DTO a la Entidad
            TicketIncidencia entidad = new TicketIncidencia();
            
            entidad.setIdTicketIncidenica(dto.getIdTicketIncidenica());
            entidad.setIdFilial(dto.getIdFilial());
            entidad.setTipoOpcion(dto.getTipoOpcion());
            entidad.setIdAbonado(dto.getIdAbonado());
            entidad.setClaseServicio(dto.getClaseServicio());
            entidad.setFechaInicio(dto.getFechaInicio());
            entidad.setHoraInicio(dto.getHoraInicio());
            entidad.setEstado(dto.getEstado());
            entidad.setMotivo(dto.getMotivo());
            entidad.setSubMotivo(dto.getSubMotivo());
            entidad.setDetalle(dto.getDetalle());
            entidad.setFechaFin(dto.getFechaFin());
            entidad.setHoraFin(dto.getHoraFin());
            entidad.setIndSolucionado(dto.getIndSolucionado());
            entidad.setTextoResolucion(dto.getTextoResolucion());
            entidad.setCategoria(dto.getCategoria());
            entidad.setCausa(dto.getCausa());
            entidad.setSubCausa(dto.getSubCausa());
            entidad.setSolucion(dto.getSolucion());
            entidad.setLatitud(dto.getLatitud());
            entidad.setLongitud(dto.getLongitud());
            entidad.setAreaEscalado(dto.getAreaEscalado());
            entidad.setIndEliminacion(dto.getIndEliminacion());
            entidad.setIdUsuarioIng(dto.getIdUsuarioIng());
            entidad.setFechaIng(dto.getFechaIng());
            entidad.setIdUsuarioUltMod(dto.getIdUsuarioUltMod());
            entidad.setFechaUltMod(dto.getFechaUltMod());
            entidad.setIdOs(dto.getIdOs());
            entidad.setResponsableIncidencia(dto.getResponsableIncidencia());
            entidad.setFormaAviso(dto.getFormaAviso());
            entidad.setAreaRegistro(dto.getAreaRegistro());
            entidad.setServicioOrigen(dto.getServicioOrigen());

            // 3. Persistencia
            ticketIncidenciaRepository.save(entidad);

        } finally {
            // 4. Liberación del ThreadLocal fundamental para Multi-Tenancy
            DBContextHolder.clear();
        }
    }
}
