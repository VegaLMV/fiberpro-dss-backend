package com.conexion.fiberpro.modules.operaciones.dtos;

import lombok.Data;

@Data
public class OrdenServicioDTO {
    private Integer idOS;
    private Integer idFilial;
    private String tipoOS;
    private Integer numeroOS;
    private Integer idAbonado;
    private String observaciones;
    private String estOS;
    private Integer idTecnico;
    private String claseServicio;
}