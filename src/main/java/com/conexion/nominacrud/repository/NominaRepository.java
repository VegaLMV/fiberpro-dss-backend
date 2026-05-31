package com.conexion.nominacrud.repository;

import com.conexion.nominacrud.entity.DetNominaCableGo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NominaRepository extends JpaRepository<DetNominaCableGo, Integer> {
    // Cambiamos 'String' por 'Integer' porque la BD dice que el ID es un 'int'
}