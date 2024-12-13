package com.easywheels.Controller;

import com.easywheels.Model.Vehiculo;
import com.easywheels.Service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehiculos")  // Ruta base para el controlador
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    // Crear un nuevo vehículo
    @PostMapping
    public ResponseEntity<Vehiculo> createVehiculo(@RequestBody Vehiculo vehiculo, @RequestParam String permiso) {
        Vehiculo nuevoVehiculo = vehiculoService.createVehiculo(vehiculo, permiso);
        return ResponseEntity.ok(nuevoVehiculo); // Retorna el vehículo creado
    }

    // Obtener vehículo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> getVehiculoById(@PathVariable Long id, @RequestParam String permiso) {
        Vehiculo vehiculo = vehiculoService.getVehiculoById(id, permiso);
        return (vehiculo != null) ? ResponseEntity.ok(vehiculo) : ResponseEntity.notFound().build();
    }

    // Obtener todos los vehículos
    @GetMapping
    public ResponseEntity<List<Vehiculo>> getAllVehiculos(@RequestParam String permiso) {
        List<Vehiculo> vehiculos = vehiculoService.getAllVehiculos(permiso);
        return ResponseEntity.ok(vehiculos);
    }

    // Actualizar un vehículo
    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> updateVehiculo(@PathVariable Long id, @RequestBody Vehiculo vehiculo,
                                                   @RequestParam String permiso) {
        Vehiculo vehiculoActualizado = vehiculoService.updateVehiculo(id, vehiculo, permiso);
        return ResponseEntity.ok(vehiculoActualizado); // Retorna el vehículo actualizado
    }

    // Eliminar un vehículo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehiculo(@PathVariable Long id, @RequestParam String permiso) {
        vehiculoService.deleteVehiculo(id, permiso);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content
    }
}
