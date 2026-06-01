package com.conexion.fiberpro.modules.clientes.services;

import com.conexion.fiberpro.config.DBContextHolder;
import com.conexion.fiberpro.modules.clientes.dtos.AbonadoDTO;
import com.conexion.fiberpro.modules.clientes.entities.Abonado;
import com.conexion.fiberpro.modules.clientes.repositories.AbonadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio de negocio para la gestión de Abonados.
 * Orquesta la transformación de DTO a Entidad y administra el enrutamiento dinámico
 * de la persistencia hacia el tenant correspondiente.
 */
@Service
public class AbonadoService {

    private final AbonadoRepository abonadoRepository;

    /**
     * Inyección de dependencias por constructor.
     *
     * @param abonadoRepository Repositorio de la entidad Abonado
     */
    @Autowired
    public AbonadoService(AbonadoRepository abonadoRepository) {
        this.abonadoRepository = abonadoRepository;
    }

    /**
     * Persiste la información de un abonado en la base de datos destino específica.
     * Aplica el patrón Multi-Tenancy configurando el contexto en el ThreadLocal.
     *
     * @param dto El objeto de transferencia con los datos del abonado a persistir.
     * @param baseDatosDestino El identificador de la base de datos del tenant objetivo.
     */
    public void registrarAbonado(AbonadoDTO dto, String baseDatosDestino) {
        try {
            DBContextHolder.setCurrentDb(baseDatosDestino);

            Abonado entidad = new Abonado();
            entidad.setIdAbonado(dto.getIdAbonado());
            entidad.setIdFilial(dto.getIdFilial());
            entidad.setTipoDocIdentidad(dto.getTipoDocIdentidad());
            entidad.setNumDocIdentidad(dto.getNumDocIdentidad());
            entidad.setPaterno(dto.getPaterno());
            entidad.setMaterno(dto.getMaterno());
            entidad.setPNombre(dto.getPNombre());
            entidad.setSNombre(dto.getSNombre());
            entidad.setRazonSocial(dto.getRazonSocial());
            entidad.setSexo(dto.getSexo());
            entidad.setCelular(dto.getCelular());
            entidad.setCorreo(dto.getCorreo());
            entidad.setEstAbonado(dto.getEstAbonado());
            entidad.setFechaEstado(dto.getFechaEstado());
            entidad.setZona(dto.getZona());
            entidad.setFechaSuscripcion(dto.getFechaSuscripcion());

            abonadoRepository.save(entidad);
        } finally {
            DBContextHolder.clear();
        }
    }
}
