package com.conexion.fiberpro.modules.nomina.services;

import com.conexion.fiberpro.config.DBContextHolder;
import com.conexion.fiberpro.modules.nomina.dtos.DetNominaCableGoDTO;
import com.conexion.fiberpro.modules.nomina.entities.DetNominaCableGo;
import com.conexion.fiberpro.modules.nomina.repositories.NominaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio de negocio para la gestión de Nómina.
 * Orquesta la transformación de DTO a Entidad y administra el enrutamiento dinámico.
 */
@Service
public class NominaService {

    private final NominaRepository nominaRepository;

    /**
     * Inyección de dependencias por constructor.
     *
     * @param nominaRepository Repositorio de la entidad DetNominaCableGo
     */
    @Autowired
    public NominaService(NominaRepository nominaRepository) {
        this.nominaRepository = nominaRepository;
    }

    /**
     * Persiste el detalle de nómina en la base de datos destino específica.
     * Aplica el patrón Multi-Tenancy configurando el contexto.
     *
     * @param dto El objeto de transferencia con los datos a persistir.
     * @param baseDatosDestino El identificador de la base de datos del tenant.
     */
    public void registrarDetalleNomina(DetNominaCableGoDTO dto, String baseDatosDestino) {
        try {
            DBContextHolder.setCurrentDb(baseDatosDestino);

            DetNominaCableGo entidad = new DetNominaCableGo();
            entidad.setIdDetNominaCableGo(dto.getIdDetNominaCableGo());
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

            nominaRepository.save(entidad);
        } finally {
            DBContextHolder.clear();
        }
    }
}