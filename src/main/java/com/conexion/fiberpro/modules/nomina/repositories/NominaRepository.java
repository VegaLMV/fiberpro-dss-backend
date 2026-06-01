package com.conexion.fiberpro.modules.nomina.repositories;

import com.conexion.fiberpro.modules.nomina.entities.DetNominaCableGo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad DetNominaCableGo.
 * Provee operaciones de persistencia encapsulando la base de datos.
 */
@Repository
public interface NominaRepository extends JpaRepository<DetNominaCableGo, Integer> {
}