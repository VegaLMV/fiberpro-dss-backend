package com.conexion.fiberpro.modules.operaciones.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TicketIncidencia")
public class TicketIncidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdTicketIncidenica", nullable = false)
    private Integer idTicketIncidenica;

    @Column(name = "IdFilial")
    private Integer idFilial;

    @Column(name = "TipoOpcion", length = 3)
    private String tipoOpcion;

    @Column(name = "IdAbonado")
    private Integer idAbonado;

    @Column(name = "ClaseServicio", length = 3)
    private String claseServicio;

    @Column(name = "FechaInicio")
    private LocalDateTime fechaInicio;

    @Column(name = "HoraInicio", length = 10)
    private String horaInicio;

    @Column(name = "Estado", length = 3)
    private String estado;

    @Column(name = "Motivo", length = 3)
    private String motivo;

    @Column(name = "SubMotivo", length = 3)
    private String subMotivo;

    @Column(name = "Detalle", length = 1024)
    private String detalle;

    @Column(name = "FechaFin")
    private LocalDateTime fechaFin;

    @Column(name = "HoraFin", length = 10)
    private String horaFin;

    @Column(name = "IndSolucionado")
    private Integer indSolucionado;

    @Column(name = "TextoResolucion", length = 1024)
    private String textoResolucion;

    @Column(name = "Categoria", length = 3)
    private String categoria;

    @Column(name = "Causa", length = 3)
    private String causa;

    @Column(name = "SubCausa", length = 3)
    private String subCausa;

    @Column(name = "Solucion", length = 3)
    private String solucion;

    @Column(name = "Latitud", length = 1024)
    private String latitud;

    @Column(name = "Longitud", length = 1024)
    private String longitud;

    @Column(name = "AreaEscalado", length = 3)
    private String areaEscalado;

    @Column(name = "IndEliminacion")
    private Integer indEliminacion;

    @Column(name = "IdUsuarioIng")
    private Integer idUsuarioIng;

    @Column(name = "FechaIng")
    private LocalDateTime fechaIng;

    @Column(name = "IdUsuarioUltMod")
    private Integer idUsuarioUltMod;

    @Column(name = "FechaUltMod")
    private LocalDateTime fechaUltMod;

    @Column(name = "IdOs")
    private Integer idOs;

    @Column(name = "ResponsableIncidencia", length = 3)
    private String responsableIncidencia;

    @Column(name = "FormaAviso", length = 3)
    private String formaAviso;

    @Column(name = "AreaRegistro", length = 3)
    private String areaRegistro;

    @Column(name = "ServicioOrigen", length = 3)
    private String servicioOrigen;
}
