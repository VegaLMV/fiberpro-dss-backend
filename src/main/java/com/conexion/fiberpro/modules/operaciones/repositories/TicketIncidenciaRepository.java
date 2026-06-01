package com.conexion.fiberpro.modules.operaciones.repositories;

import com.conexion.fiberpro.modules.operaciones.entities.TicketIncidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketIncidenciaRepository extends JpaRepository<TicketIncidencia, Integer> {
}
