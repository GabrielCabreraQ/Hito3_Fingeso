package com.easywheels.Repository;

import com.easywheels.Model.Arrendatario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArrendatarioRepository extends JpaRepository<Arrendatario, Long> {
    Arrendatario findByCorreoUsuario(String correoUsuario);  // Cambiado a "correoUsuario"
}
