package com.conexion.fiberpro.modules.operaciones.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TicketIncidenciaDTO {
    private Integer idTicketIncidenica;
    private Integer idFilial;
    private String tipoOpcion;
    private Integer idAbonado;
    private String claseServicio;
    private LocalDateTime fechaInicio;
    private String horaInicio;
    private String estado;
    private String motivo;
    private String subMotivo;
    private String detalle;
    private LocalDateTime fechaFin;
    private String horaFin;
    private Integer indSolucionado;
    private String textoResolucion;
    private String categoria;
    private String causa;
    private String subCausa;
    private String solucion;
    private String latitud;
    private String longitud;
    private String areaEscalado;
    private Integer indEliminacion;
    private Integer idUsuarioIng;
    private LocalDateTime fechaIng;
    private Integer idUsuarioUltMod;
    private LocalDateTime fechaUltMod;
    private Integer idOs;
    private String responsableIncidencia;
    private String formaAviso;
    private String areaRegistro;
    private String servicioOrigen;
}
