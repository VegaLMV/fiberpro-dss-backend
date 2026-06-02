package com.conexion.fiberpro.modules.operaciones.repositories;

import com.conexion.fiberpro.modules.operaciones.entities.OrdenServicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdenServicioRepository extends JpaRepository<OrdenServicio, Integer> {

  @Query("SELECT o FROM OrdenServicio o WHERE " +
      "(:numeroOS IS NULL OR o.numeroOS = :numeroOS) AND " +
      "(:idAbonado IS NULL OR o.idAbonado = :idAbonado) AND " +
      "(:tipoOS IS NULL OR :tipoOS = '' OR o.tipoOS = :tipoOS) AND " +
      "(:estOS IS NULL OR :estOS = '' OR o.estOS = :estOS) AND " +
      "(o.estOS != 'ANU')")
  Page<OrdenServicio> busquedaAvanzada(
      @Param("numeroOS") Integer numeroOS,
      @Param("idAbonado") Integer idAbonado,
      @Param("tipoOS") String tipoOS,
      @Param("estOS") String estOS,
      Pageable pageable);
}