package com.easywheels.Service;


import com.easywheels.Model.Informe;
import com.easywheels.Repository.InformeRepository;
import com.easywheels.Repository.VehiculoRepository;
import com.easywheels.Model.Vehiculo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    @Autowired
    private InformeRepository informeRepository;

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

    //Crear vehiculo
    public Vehiculo createVehiculo(Vehiculo vehiculo, String permiso) {
        verificarPermisosAdmin(permiso);
        // Asegúrate de que el vehículo no tenga un ID establecido
        //vehiculo.setIdVehiculo(null); // Esto es redundante pero asegura que el ID es nulo
        return vehiculoRepository.save(vehiculo);
    }

    // Read
    public Vehiculo getVehiculoById(Long id, String permiso) {
        verificarPermisosAdmin(permiso);
        return vehiculoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vehículo con ID " + id + " no encontrado."));
    }

    //Obtener todos los vehiculos, solo puede hacerlo el admin.
    public List<Vehiculo> getAllVehiculos(String permiso) {
        verificarPermisosAdmin(permiso);
        return vehiculoRepository.findAll();
    }

    //Actualizar información de un vehiculo
    public Vehiculo updateVehiculo(Long id, Vehiculo vehiculo, String permiso) {
        verificarPermisosAdmin(permiso);
        if (vehiculoRepository.existsById(id)) {
            vehiculo.setIdVehiculo(id);
            return vehiculoRepository.save(vehiculo);
        }
        return null;
    }


    //Eliminar un vehiculo de la base de datos
    public void deleteVehiculo(Long id, String permiso) {
        verificarPermisosAdmin(permiso);
        if (!vehiculoRepository.existsById(id)) {
            throw new IllegalArgumentException("Vehículo con ID " + id + " no existe.");
        }
        vehiculoRepository.deleteById(id);
    }

    //Método que devuelve los vehículos disponibles en una fecha específica
    public List<Vehiculo> obtenerVehiculosDisponibles(LocalDate fecha, String permiso) {
        verificarPermisosAdmin(permiso);
        // Llama al repositorio para encontrar vehículos disponibles
        return vehiculoRepository.findVehiculosDisponibles(fecha);
    }

    //Informar si existen fallas asociadas al vehiculo
    @Transactional
    public Informe procesarArriendo(Long idVehiculo, String observaciones) {
        //Buscar el vehículo
        Vehiculo vehiculo = vehiculoRepository.findById(idVehiculo)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        //Crear un nuevo informe
        Informe nuevoInforme = new Informe(vehiculo, observaciones);

        //Agregar el informe al vehículo
        vehiculo.agregarInforme(nuevoInforme);

        //Actualizar disponible_uso basado en el informe más reciente
        if (observaciones.equalsIgnoreCase("No tiene fallas")) {
            vehiculo.setDisponible_uso(true);
        } else if (observaciones.equalsIgnoreCase("Presenta fallas")) {
            vehiculo.setDisponible_uso(false);
        } else {
            throw new IllegalArgumentException("Observaciones inválidas: " + observaciones);
        }

        //Guardar cambios en la base de datos
        vehiculoRepository.save(vehiculo);

        return nuevoInforme;
    }

    //Obtener todos los informes de un vehículo
    public List<Informe> obtenerInformesPorVehiculo(Long idVehiculo) {
        // Verificar si el vehículo existe
        vehiculoRepository.findById(idVehiculo)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        // Retornar los informes asociados al vehículo
        return informeRepository.findByVehiculoIdVehiculo(idVehiculo);
    }

}
