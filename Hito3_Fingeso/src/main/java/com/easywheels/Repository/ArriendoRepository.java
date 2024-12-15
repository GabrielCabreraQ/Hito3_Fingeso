package com.easywheels.Repository;

import com.easywheels.Model.Arriendo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArriendoRepository extends JpaRepository<Arriendo, Integer> {
    // JpaRepository ya incluye el método save por defecto.
    @Query("SELECT a FROM Arriendo a WHERE a.vehiculo.idVehiculo = :vehiculoId")
    List<Arriendo> findByVehiculoId(@Param("vehiculoId") Long vehiculoId);
}