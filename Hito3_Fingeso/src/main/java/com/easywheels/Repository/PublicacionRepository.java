package com.easywheels.Repository;

import com.easywheels.Model.Publicacion;
import com.easywheels.Model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
    boolean existsByVehiculo(Vehiculo vehiculo);
}

