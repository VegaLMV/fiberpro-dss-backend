package com.conexion.fiberpro.modules.finanzas.services;

import com.conexion.fiberpro.modules.finanzas.dtos.AbonoDTO;
import com.conexion.fiberpro.modules.finanzas.entities.Abono;
import com.conexion.fiberpro.modules.finanzas.repositories.AbonoRepository;
import com.conexion.fiberpro.config.DBContextHolder; // Ajusta este import a la ruta real de tu clase
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AbonoService {

    private final AbonoRepository abonoRepository;

    @Autowired
    public AbonoService(AbonoRepository abonoRepository) {
        this.abonoRepository = abonoRepository;
    }

    public void registrarAbono(AbonoDTO dto, String baseDatosDestino) {
        try {
            // 1. Enrutamiento dinámico: Selección de base de datos objetivo
            DBContextHolder.setCurrentDb(baseDatosDestino);

            // 2. Mapeo manual del DTO a la Entidad
            Abono abono = new Abono();
            
            // Si el idAbono es autoincremental en inserción se puede obviar, 
            // pero lo agregamos por si la lógica también aplica a actualizaciones
            abono.setIdAbono(dto.getIdAbono()); 
            
            abono.setIdFilial(dto.getIdFilial());
            abono.setIdAbonado(dto.getIdAbonado());
            abono.setIdOS(dto.getIdOS());
            abono.setCodigoOperacion(dto.getCodigoOperacion());
            abono.setServicioOrigen(dto.getServicioOrigen());
            abono.setFechaRegistro(dto.getFechaRegistro());
            abono.setPeriodo(dto.getPeriodo());
            abono.setConcepto(dto.getConcepto());
            abono.setImporte(dto.getImporte());
            abono.setClaseOperacionDestino(dto.getClaseOperacionDestino());
            abono.setEstado(dto.getEstado());
            abono.setIdUsuarioIng(dto.getIdUsuarioIng());
            abono.setFechaIng(dto.getFechaIng());
            abono.setIdUsuarioMod(dto.getIdUsuarioMod());
            abono.setFechaUltMod(dto.getFechaUltMod());
            abono.setIdUsuarioAprueba(dto.getIdUsuarioAprueba());
            abono.setFechaAprueba(dto.getFechaAprueba());
            abono.setIdAplicacionDescuento(dto.getIdAplicacionDescuento());
            abono.setIndDescuento(dto.getIndDescuento());
            abono.setCodMoneda(dto.getCodMoneda());
            abono.setIdServicioOtros(dto.getIdServicioOtros());
            abono.setIdAplicacionDescuentoCupon(dto.getIdAplicacionDescuentoCupon());

            // 3. Persistencia delegada al repositorio
            abonoRepository.save(abono);

        } finally {
            // 4. Limpieza OBLIGATORIA del ThreadLocal para prevenir memory leaks
            // y que conexiones de otros requests usen el esquema incorrecto
            DBContextHolder.clear();
        }
    }
}