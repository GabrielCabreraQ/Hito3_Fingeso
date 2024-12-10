package com.easywheels.Service;

import com.easywheels.Repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehiculoService {      //Incluso, creo que esta clase no se usa
    @Autowired       // Inyectar dependencias automaticaente en Spring
    private VehiculoRepository vehiculoRepository;      // Llamada al repo
}
