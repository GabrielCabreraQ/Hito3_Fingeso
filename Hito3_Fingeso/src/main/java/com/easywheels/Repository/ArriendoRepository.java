package com.easywheels.Repository;

import com.easywheels.Model.Arriendo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArriendoRepository extends JpaRepository<Arriendo, Integer> {
    // JpaRepository ya incluye el método save por defecto.
}