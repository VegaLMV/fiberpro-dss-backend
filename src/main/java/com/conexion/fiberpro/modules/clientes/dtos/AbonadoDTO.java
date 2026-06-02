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
    private String telefono;
    private String numCasa;
    private String numDispositivo;
    @org.springframework.format.annotation.DateTimeFormat(pattern = "yyyy-MM-dd")
    private java.time.LocalDate fechaNacimiento;
    private String estAbonado;
    private LocalDateTime fechaEstado;
    private String zona;
    private LocalDateTime fechaSuscripcion;
    private Integer indEliminacionUsuario;
}
