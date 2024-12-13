package com.easywheels.Service;

import com.easywheels.Repository.VehiculoRepository;
import com.easywheels.Model.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoService {
    @Autowired
    private VehiculoRepository vehiculoRepository;

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
        return vehiculoRepository.save(vehiculo); // No necesitas establecer el ID
    }

    // Read
    public Vehiculo getVehiculoById(Long id, String permiso) {
        verificarPermisosAdmin(permiso);
        return vehiculoRepository.findById(id).orElse(null);
    }

    public List<Vehiculo> getAllVehiculos(String permiso) {
        verificarPermisosAdmin(permiso);
        return vehiculoRepository.findAll();
    }

    // Update
    public Vehiculo updateVehiculo(Long id, Vehiculo vehiculo, String permiso) {
        verificarPermisosAdmin(permiso);

        // Verifica si el vehiculo existe
        if (!vehiculoRepository.existsById(id)) {
            throw new IllegalArgumentException("Vehículo con ID " + id + " no existe.");
        }

        vehiculo.setIdVehiculo(id); // Asegúrate de establecer el ID para la actualización
        return vehiculoRepository.save(vehiculo);
    }

    // Delete
    public void deleteVehiculo(Long id, String permiso) {
        verificarPermisosAdmin(permiso);
        vehiculoRepository.deleteById(id);
    }
}
