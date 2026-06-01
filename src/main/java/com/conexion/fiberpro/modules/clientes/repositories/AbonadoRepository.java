package com.conexion.fiberpro.modules.clientes.repositories;

import com.conexion.fiberpro.modules.clientes.entities.Abonado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio JPA para la entidad Abonado.
 * Provee operaciones estándar de acceso a datos sin acoplar detalles de implementación.
 */
@Repository
public interface AbonadoRepository extends JpaRepository<Abonado, Integer> {
}
