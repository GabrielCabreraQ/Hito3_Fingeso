package com.easywheels.Service;

import com.easywheels.Repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehiculoService {
    @Autowired       // Inyectar dependencias automaticaente en Spring
    private VehiculoRepository vehiculoRepository;      // Llamada al repo
}
