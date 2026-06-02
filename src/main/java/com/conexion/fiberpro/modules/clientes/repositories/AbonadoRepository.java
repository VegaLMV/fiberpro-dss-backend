package com.conexion.fiberpro.modules.clientes.repositories;

import com.conexion.fiberpro.modules.clientes.entities.Abonado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AbonadoRepository extends JpaRepository<Abonado, Integer> {
  // Trae solo los últimos 50 abonados registrados
  List<Abonado> findTop50ByOrderByIdAbonadoDesc();
  // Filtro de Búsqueda Dinámica + Paginación
  Page<Abonado> findBypNombreContainingIgnoreCaseOrNumDocIdentidadContainingIgnoreCase(
      String pNombre, String numDocIdentidad, Pageable pageable);
  @Query("SELECT a FROM Abonado a WHERE " +
      "(:tipoDoc IS NULL OR :tipoDoc = '' OR a.tipoDocIdentidad = :tipoDoc) AND " +
      "(:numDoc IS NULL OR :numDoc = '' OR a.numDocIdentidad LIKE %:numDoc%) AND " +
      "(:nombres IS NULL OR :nombres = '' OR LOWER(a.pNombre) LIKE LOWER(CONCAT('%', :nombres, '%'))) AND " +
      "(:paterno IS NULL OR :paterno = '' OR LOWER(a.paterno) LIKE LOWER(CONCAT('%', :paterno, '%'))) AND " +
      "(:materno IS NULL OR :materno = '' OR LOWER(a.materno) LIKE LOWER(CONCAT('%', :materno, '%'))) AND " +
      "(:estado IS NULL OR :estado = '' OR a.estAbonado = :estado) AND " +
      "(a.indEliminacionUsuario IS NULL OR a.indEliminacionUsuario = 0)")
  Page<Abonado> busquedaAvanzada(
      @Param("tipoDoc") String tipoDoc,
      @Param("numDoc") String numDoc,
      @Param("nombres") String nombres,
      @Param("paterno") String paterno,
      @Param("materno") String materno,
      @Param("estado") String estado,
      Pageable pageable);
}