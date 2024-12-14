package com.easywheels.Service;

import com.easywheels.Repository.VehiculoRepository;
import com.easywheels.Model.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    @Autowired
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
        // Asegúrate de que el vehículo no tenga un ID establecido
        vehiculo.setIdVehiculo(null); // Esto es redundante pero asegura que el ID es nulo
        return vehiculoRepository.save(vehiculo);
    }

    // Read
    public Vehiculo getVehiculoById(Long id, String permiso) {
        verificarPermisosAdmin(permiso);
        return vehiculoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vehículo con ID " + id + " no encontrado."));
    }

    public List<Vehiculo> getAllVehiculos(String permiso) {
        verificarPermisosAdmin(permiso);
        return vehiculoRepository.findAll();
    }

    // Update
    public Vehiculo updateVehiculo(Long id, Vehiculo vehiculo, String permiso) {
        verificarPermisosAdmin(permiso);
        if (!vehiculoRepository.existsById(id)) {
            throw new IllegalArgumentException("Vehículo con ID " + id + " no existe.");
        }
        vehiculo.setIdVehiculo(id); // Asegúrate de establecer el ID para la actualización
        return vehiculoRepository.save(vehiculo);
    }

    // Delete
    public void deleteVehiculo(Long id, String permiso) {
        verificarPermisosAdmin(permiso);
        if (!vehiculoRepository.existsById(id)) {
            throw new IllegalArgumentException("Vehículo con ID " + id + " no existe.");
        }
        vehiculoRepository.deleteById(id);
    }

    // Método que devuelve los vehículos disponibles en una fecha específica
    public List<Vehiculo> obtenerVehiculosDisponibles(LocalDate fecha, String permiso) {
        verificarPermisosAdmin(permiso);
        // Llama al repositorio para encontrar vehículos disponibles
        return vehiculoRepository.findVehiculosDisponibles(fecha);
    }


}
