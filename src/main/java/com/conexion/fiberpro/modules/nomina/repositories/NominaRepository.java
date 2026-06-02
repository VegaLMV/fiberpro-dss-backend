package com.conexion.fiberpro.modules.nomina.repositories;

import com.conexion.fiberpro.modules.nomina.entities.DetNominaCableGo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NominaRepository extends JpaRepository<DetNominaCableGo, Integer> {

  // Filtro Avanzado: Búsqueda dinámica y omisión de eliminados lógicamente
  @Query("SELECT n FROM DetNominaCableGo n WHERE " +
      "(:nombres IS NULL OR :nombres = '' OR LOWER(n.nombres) LIKE LOWER(CONCAT('%', :nombres, '%'))) AND " +
      "(:docIdent IS NULL OR :docIdent = '' OR LOWER(n.docIdent) LIKE LOWER(CONCAT('%', :docIdent, '%'))) AND " +
      "(:estado IS NULL OR :estado = '' OR n.estadoServicio = :estado) AND " +
      "(:filial IS NULL OR :filial = '' OR LOWER(n.filial) LIKE LOWER(CONCAT('%', :filial, '%'))) AND " +
      "(n.estadoServicio IS NULL OR n.estadoServicio != 'ELIMINADO')")
  Page<DetNominaCableGo> busquedaAvanzada(
      @Param("nombres") String nombres,
      @Param("docIdent") String docIdent,
      @Param("estado") String estado,
      @Param("filial") String filial,
      Pageable pageable);
}