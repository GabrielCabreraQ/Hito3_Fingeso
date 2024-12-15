package com.easywheels.Repository;

import com.easywheels.Model.Informe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformeRepository extends JpaRepository<Informe, Long> {

    List<Informe> findByVehiculoIdVehiculo(Long idVehiculo);

}
