package com.conexion.fiberpro.modules.finanzas.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AbonoDTO {
    private Integer idAbono;
    private Integer idFilial;
    private Integer idAbonado;
    private Integer idOS;
    private String codigoOperacion;
    private String servicioOrigen;
    private LocalDateTime fechaRegistro;
    private Integer periodo;
    private String concepto;
    private BigDecimal importe;
    private String claseOperacionDestino;
    private String estado;
    private Integer idUsuarioIng;
    private LocalDateTime fechaIng;
    private Integer idUsuarioMod;
    private LocalDateTime fechaUltMod;
    private Integer idUsuarioAprueba;
    private LocalDateTime fechaAprueba;
    private Integer idAplicacionDescuento;
    private Integer indDescuento;
    private String codMoneda;
    private Integer idServicioOtros;
    private Integer idAplicacionDescuentoCupon;
}
