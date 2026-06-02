package com.conexion.fiberpro.modules.clientes.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Entidad principal para la tabla Abonado.
 * Mapea exclusivamente las columnas críticas del núcleo estratégico del negocio,
 * evitando asimetrías de esquema entre diferentes bases de datos transaccionales.
 */
@Data
@Entity
@Getter
@Setter
@Table(name = "Abonado")
public class Abonado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdAbonado", nullable = false)
    private Integer idAbonado;

    @Column(name = "idFilial", nullable = false)
    private Integer idFilial;

    @Column(name = "TipoDocIdentidad", length = 3)
    private String tipoDocIdentidad;

    @Column(name = "NumDocIdentidad", length = 100)
    private String numDocIdentidad;

    @Column(name = "Paterno", length = 30)
    private String paterno;

    @Column(name = "Materno", length = 30)
    private String materno;

    @Column(name = "PNombre", length = 30)
    private String pNombre;

    @Column(name = "SNombre", length = 30)
    private String sNombre;

    @Column(name = "RazonSocial", length = 80)
    private String razonSocial;

    @Column(name = "Sexo", length = 3)
    private String sexo;

    @Column(name = "Celular", length = 100)
    private String celular;

    @Column(name = "Correo", length = 1024)
    private String correo;

    @Column(name = "Telefono", length = 100)
    private String telefono;

    @Column(name = "NumCasa", length = 100)
    private String numCasa;

    @Column(name = "NumDispositivo", length = 100)
    private String numDispositivo;

    @Column(name = "FechaNacimiento")
    private java.time.LocalDate fechaNacimiento;
    @Transient
    public Integer getEdad() {
        if (this.fechaNacimiento == null) {
            return null;
        }
        return java.time.Period.between(this.fechaNacimiento, java.time.LocalDate.now()).getYears();
    }

    @Column(name = "EstAbonado", length = 3)
    private String estAbonado;

    @Column(name = "FechaEstado")
    private LocalDateTime fechaEstado;

    @Column(name = "Zona", length = 3)
    private String zona;

    @Column(name = "FechaSuscripcion")
    private LocalDateTime fechaSuscripcion;

    @Column(name = "IndEliminacionUsuario")
    private Integer indEliminacionUsuario;
}
