package com.conexion.fiberpro.modules.finanzas.repositories;

import com.conexion.fiberpro.modules.finanzas.entities.Abono;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AbonoRepository extends JpaRepository<Abono, Integer> {

  // Filtro Avanzado: Ignora nulos y omite transacciones anuladas (ANU)
  @Query("SELECT a FROM Abono a WHERE " +
      "(:idAbonado IS NULL OR a.idAbonado = :idAbonado) AND " +
      "(:periodo IS NULL OR a.periodo = :periodo) AND " +
      "(:concepto IS NULL OR :concepto = '' OR LOWER(a.concepto) LIKE LOWER(CONCAT('%', :concepto, '%'))) AND " +
      "(:estado IS NULL OR :estado = '' OR a.estado = :estado) AND " +
      "(a.estado != 'ANU')")
  Page<Abono> busquedaAvanzada(
      @Param("idAbonado") Integer idAbonado,
      @Param("periodo") Integer periodo,
      @Param("concepto") String concepto,
      @Param("estado") String estado,
      Pageable pageable);
}