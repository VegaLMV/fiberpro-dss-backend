package com.conexion.fiberpro.modules.finanzas.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Getter
@Setter
@Table(name = "Abono")
public class Abono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdAbono", nullable = false)
    private Integer idAbono;

    @Column(name = "IdFilial", nullable = false)
    private Integer idFilial;

    @Column(name = "IdAbonado", nullable = false)
    private Integer idAbonado;

    @Column(name = "IdOS")
    private Integer idOS;

    @Column(name = "CodigoOperacion", length = 3)
    private String codigoOperacion;

    @Column(name = "ServicioOrigen", length = 3)
    private String servicioOrigen;

    @Column(name = "FechaRegistro")
    private LocalDateTime fechaRegistro;

    @Column(name = "Periodo")
    private Integer periodo;

    @Column(name = "Concepto", length = 256)
    private String concepto;

    @Column(name = "Importe")
    private BigDecimal importe;

    @Column(name = "ClaseOperacionDestino", length = 3)
    private String claseOperacionDestino;

    @Column(name = "Estado", length = 3)
    private String estado;

    @Column(name = "idUsuarioIng")
    private Integer idUsuarioIng;

    @Column(name = "FechaIng")
    private LocalDateTime fechaIng;

    @Column(name = "IdUsuarioMod")
    private Integer idUsuarioMod;

    @Column(name = "FechaUltMod")
    private LocalDateTime fechaUltMod;

    @Column(name = "IdUsuarioAprueba")
    private Integer idUsuarioAprueba;

    @Column(name = "FechaAprueba")
    private LocalDateTime fechaAprueba;

    @Column(name = "idAplicacionDescuento")
    private Integer idAplicacionDescuento;

    @Column(name = "IndDescuento")
    private Integer indDescuento;

    @Column(name = "CodMoneda", length = 3)
    private String codMoneda;

    @Column(name = "IdServicioOtros")
    private Integer idServicioOtros;

    @Column(name = "idAplicacionDescuentoCupon")
    private Integer idAplicacionDescuentoCupon;
}