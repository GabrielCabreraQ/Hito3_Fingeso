package com.easywheels.Repository;

import com.easywheels.Model.Administrador; // Asegúrate de importar Administrador
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
}