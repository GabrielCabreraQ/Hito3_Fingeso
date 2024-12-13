package com.easywheels.Service;

import com.easywheels.Repository.VehiculoRepository;
import  com.easywheels.Model.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoService {
    @Autowired       // Inyectar dependencias automaticaente en Spring
    private VehiculoRepository vehiculoRepository;      // Llamada al repo

    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    // Método para verificar el permiso
    private void verificarPermisosAdmin(String permiso) {
        if (!"administrador".equals(permiso)) {
            throw new IllegalStateException("No tienes permisos para esta acción.");
        }
    }


    // CRUD con validación de permisos
    // Create
    public Vehiculo createVehiculo(Vehiculo vehiculo, String permiso) {
        verificarPermisosAdmin(permiso);
        return vehiculoRepository.save(vehiculo);
    }

    // Read
    public Vehiculo getVehiculoById(int id, String permiso) {
        verificarPermisosAdmin(permiso);
        return vehiculoRepository.findById((long) id).orElse(null);
    }

    public List<Vehiculo> getAllVehiculos(String permiso) {
        verificarPermisosAdmin(permiso);
        return vehiculoRepository.findAll();
    }

    // Update
    public Vehiculo updateVehiculo(Vehiculo vehiculo, String permiso) {
        verificarPermisosAdmin(permiso);
        return vehiculoRepository.save(vehiculo);
    }

    // Delete
    public void deleteVehiculo(int id, String permiso) {
        verificarPermisosAdmin(permiso);
        vehiculoRepository.deleteById((long) id);
    }

}
