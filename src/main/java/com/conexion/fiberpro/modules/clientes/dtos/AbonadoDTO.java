package com.conexion.fiberpro.modules.clientes.dtos;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * Data Transfer Object para la entidad Abonado.
 * Encapsula la información crítica del abonado para el transporte seguro
 * entre la capa de presentación y la capa de servicios.
 */
@Data
public class AbonadoDTO {
    private Integer idAbonado;
    private Integer idFilial;
    private String tipoDocIdentidad;
    private String numDocIdentidad;
    private String paterno;
    private String materno;
    private String pNombre;
    private String sNombre;
    private String razonSocial;
    private String sexo;
    private String celular;
    private String correo;
    private String estAbonado;
    private LocalDateTime fechaEstado;
    private String zona;
    private LocalDateTime fechaSuscripcion;
}
