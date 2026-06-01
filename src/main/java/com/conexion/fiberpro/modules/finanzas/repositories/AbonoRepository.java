package com.conexion.fiberpro.modules.finanzas.repositories;

import com.conexion.fiberpro.modules.finanzas.entities.Abono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbonoRepository extends JpaRepository<Abono, Integer> {
}
