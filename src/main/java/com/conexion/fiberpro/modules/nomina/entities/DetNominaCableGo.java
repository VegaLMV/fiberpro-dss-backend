package com.conexion.fiberpro.modules.nomina.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entidad transaccional para la tabla Det_NominaCableGo.
 * Mapea los 32 atributos definidos en el diccionario de datos.
 */
@Data
@Entity
@Table(name = "Det_NominaCableGo")
public class DetNominaCableGo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdDet_NominaCableGo", nullable = false)
    private Integer idDetNominaCableGo;

    @Column(name = "IdCab_NominaCableGo")
    private Integer idCabNominaCableGo;

    @Column(name = "Origen", length = 8000)
    private String origen;

    @Column(name = "Abonado", length = 20)
    private String abonado;

    @Column(name = "EstadoServicio", length = 8000)
    private String estadoServicio;

    @Column(name = "Nombres", length = 8000)
    private String nombres;

    @Column(name = "DocIdent", length = 50)
    private String docIdent;

    @Column(name = "Correo", length = 200)
    private String correo;

    @Column(name = "DiaFacturacion")
    private Integer diaFacturacion;

    @Column(name = "Accesos", length = 8000)
    private String accesos;

    @Column(name = "Grabacion", length = 8000)
    private String grabacion;

    @Column(name = "PaquetePrincipal", length = 8000)
    private String paquetePrincipal;

    @Column(name = "PaquetesADD", length = 8000)
    private String paquetesADD;

    @Column(name = "TotalMensual")
    private BigDecimal totalMensual;

    @Column(name = "FechaSuscripcion", length = 50)
    private String fechaSuscripcion;

    @Column(name = "FechaCancelacion")
    private LocalDateTime fechaCancelacion;

    @Column(name = "MotivoCancelacion", length = 8000)
    private String motivoCancelacion;

    @Column(name = "Celular", length = 50)
    private String celular;

    @Column(name = "PerfilAsignado", length = 8000)
    private String perfilAsignado;

    @Column(name = "Afiliaciones", length = 8000)
    private String afiliaciones;

    @Column(name = "FechaAfiliacion", length = 20)
    private String fechaAfiliacion;

    @Column(name = "FechaUltPago")
    private LocalDateTime fechaUltPago;

    @Column(name = "Vendedor", length = 1000)
    private String vendedor;

    @Column(name = "Filial", length = 100)
    private String filial;

    @Column(name = "OrigenVenta", length = 100)
    private String origenVenta;

    @Column(name = "Usuario", length = 100)
    private String usuario;

    @Column(name = "Categoria", length = 100)
    private String categoria;

    @Column(name = "FechaInstalacion", length = 50)
    private String fechaInstalacion;

    @Column(name = "TarifaVigente", length = 50)
    private String tarifaVigente;

    @Column(name = "PrecioTarifa", length = 50)
    private String precioTarifa;

    @Column(name = "CanalAtencion", length = 100)
    private String canalAtencion;

    @Column(name = "TarifaPlanInicialContrato", length = 100)
    private String tarifaPlanInicialContrato;
}