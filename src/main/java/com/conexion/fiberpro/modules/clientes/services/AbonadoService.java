package com.conexion.fiberpro.modules.clientes.services;

import com.conexion.fiberpro.config.DBContextHolder;
import com.conexion.fiberpro.modules.clientes.dtos.AbonadoDTO;
import com.conexion.fiberpro.modules.clientes.entities.Abonado;
import com.conexion.fiberpro.modules.clientes.repositories.AbonadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbonadoService {

    private final AbonadoRepository abonadoRepository;

    @Autowired
    public AbonadoService(AbonadoRepository abonadoRepository) {
        this.abonadoRepository = abonadoRepository;
    }

    // 1. CREATE
    public void createAbonado(AbonadoDTO dto, String baseDatosDestino) {
        try {
            DBContextHolder.setCurrentDb(baseDatosDestino);
            Abonado entidad = new Abonado();
            mapearDtoAEntidad(dto, entidad);
            abonadoRepository.save(entidad);
        } finally {
            DBContextHolder.clear();
        }
    }

    // 2. READ (Un solo registro)
    public Abonado readAbonado(Integer id, String baseDatosDestino) {
        try {
            DBContextHolder.setCurrentDb(baseDatosDestino);
            return abonadoRepository.findById(id).orElse(null);
        } finally {
            DBContextHolder.clear();
        }
    }

    // 3. READ ALL (Lista completa)
    public List<Abonado> readAllAbonados(String baseDatosDestino) {
        try {
            DBContextHolder.setCurrentDb(baseDatosDestino);
            return abonadoRepository.findTop50ByOrderByIdAbonadoDesc();
        } finally {
            DBContextHolder.clear();
        }
    }

    // 4. UPDATE (Aquí está el método que solicitaste)
    public void updateAbonado(Integer id, AbonadoDTO dto, String baseDatosDestino) {
        try {
            DBContextHolder.setCurrentDb(baseDatosDestino);
            Optional<Abonado> abonadoExistente = abonadoRepository.findById(id);

            if (abonadoExistente.isPresent()) {
                Abonado entidad = abonadoExistente.get();
                mapearDtoAEntidad(dto, entidad);
                // JPA hace un UPDATE automáticamente porque la entidad ya tiene un ID
                abonadoRepository.save(entidad);
            }
        } finally {
            DBContextHolder.clear();
        }
    }

    // 5. DELETE
    public void deleteAbonado(Integer id, String baseDatosDestino) {
        try {
            DBContextHolder.setCurrentDb(baseDatosDestino);

            Abonado abonado = abonadoRepository.findById(id).orElse(null);
            if(abonado != null) {
                // ELIMINACIÓN LÓGICA: Cambia estado a BAJA y enciende la bandera
                abonado.setEstAbonado("BAJ");
                abonado.setIndEliminacionUsuario(1);
                abonadoRepository.save(abonado);
            }
        } finally {
            DBContextHolder.clear();
        }
    }

    public Page<Abonado> readPaginatedAbonadosAvanzado(
        String db, String tipoDoc, String numDoc, String nombres,
        String paterno, String materno, String estado, Pageable pageable) {
        try {
            DBContextHolder.setCurrentDb(db);
            return abonadoRepository.busquedaAvanzada(tipoDoc, numDoc, nombres, paterno, materno, estado, pageable);
        } finally {
            DBContextHolder.clear();
        }
    }
    private void mapearDtoAEntidad(AbonadoDTO dto, Abonado entidad) {
        entidad.setIdFilial(dto.getIdFilial() != null ? dto.getIdFilial() : 1);
        entidad.setTipoDocIdentidad(dto.getTipoDocIdentidad());
        entidad.setNumDocIdentidad(dto.getNumDocIdentidad());
        entidad.setPaterno(dto.getPaterno());
        entidad.setMaterno(dto.getMaterno());
        entidad.setPNombre(dto.getPNombre());
        entidad.setSNombre(dto.getSNombre());
        entidad.setFechaNacimiento(dto.getFechaNacimiento());
        entidad.setNumCasa(dto.getNumCasa());
        entidad.setNumDispositivo(dto.getNumDispositivo());
        entidad.setTelefono(dto.getTelefono());
        entidad.setRazonSocial(dto.getRazonSocial());
        entidad.setSexo(dto.getSexo());
        entidad.setCelular(dto.getCelular());
        entidad.setCorreo(dto.getCorreo());
        entidad.setEstAbonado(dto.getEstAbonado());
        entidad.setFechaEstado(dto.getFechaEstado());
        entidad.setZona(dto.getZona());
        entidad.setFechaSuscripcion(dto.getFechaSuscripcion());
    }
}