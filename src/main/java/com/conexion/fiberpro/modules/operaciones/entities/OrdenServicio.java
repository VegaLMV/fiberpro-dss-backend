package com.conexion.fiberpro.modules.operaciones.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "OrdenServicio")
public class OrdenServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdOS")
    private Integer idOS;

    @Column(name = "idFilial")
    private Integer idFilial;

    @Column(name = "TipoOS", length = 3)
    private String tipoOS;

    @Column(name = "NumeroOS")
    private Integer numeroOS;

    @Column(name = "IdAbonado")
    private Integer idAbonado;

    @Column(name = "FechaRecepcion")
    private LocalDateTime fechaRecepcion;

    @Column(name = "Observaciones", length = 500)
    private String observaciones;

    @Column(name = "EstOS", length = 3)
    private String estOS;

    @Column(name = "IdTecnico")
    private Integer idTecnico;

    @Column(name = "ClaseServicio", length = 3)
    private String claseServicio;

    @Column(name = "FechaUltMod")
    private LocalDateTime fechaUltMod;
}