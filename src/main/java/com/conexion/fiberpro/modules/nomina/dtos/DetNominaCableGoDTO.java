package com.conexion.fiberpro.modules.nomina.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Data Transfer Object para la entidad DetNominaCableGo.
 * Encapsula la información para el transporte entre la capa de presentación y la capa de servicios.
 */
@Data
public class DetNominaCableGoDTO {
    private Integer idDetNominaCableGo;
    private Integer idCabNominaCableGo;
    private String origen;
    private String abonado;
    private String estadoServicio;
    private String nombres;
    private String docIdent;
    private String correo;
    private Integer diaFacturacion;
    private String accesos;
    private String grabacion;
    private String paquetePrincipal;
    private String paquetesADD;
    private BigDecimal totalMensual;
    private String fechaSuscripcion;
    private LocalDateTime fechaCancelacion;
    private String motivoCancelacion;
    private String celular;
    private String perfilAsignado;
    private String afiliaciones;
    private String fechaAfiliacion;
    private LocalDateTime fechaUltPago;
    private String vendedor;
    private String filial;
    private String origenVenta;
    private String usuario;
    private String categoria;
    private String fechaInstalacion;
    private String tarifaVigente;
    private String precioTarifa;
    private String canalAtencion;
    private String tarifaPlanInicialContrato;
}