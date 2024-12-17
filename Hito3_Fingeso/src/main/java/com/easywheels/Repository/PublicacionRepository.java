package com.easywheels.Repository;

import com.easywheels.Model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
    // Consulta personalizada para filtrar por visibilidad
    List<Publicacion> findByVisibilidadTrue();
}

