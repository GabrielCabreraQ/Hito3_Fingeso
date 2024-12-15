package com.easywheels.Repository;

import com.easywheels.Model.Informe;
import com.easywheels.Model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    @Query("SELECT v FROM Vehiculo v WHERE :fecha MEMBER OF v.disponibilidad AND v.devuelto = false")
    List<Vehiculo> findVehiculosDisponibles(@Param("fecha") LocalDate fecha);
}