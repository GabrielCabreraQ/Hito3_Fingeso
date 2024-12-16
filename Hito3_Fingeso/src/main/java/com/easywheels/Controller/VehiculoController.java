package com.easywheels.Controller;

import com.easywheels.Model.Informe;
import com.easywheels.Model.Vehiculo;
import com.easywheels.Service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/vehiculos")
@CrossOrigin
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    //Crear un nuevo vehículo
    @PostMapping
    public ResponseEntity<Vehiculo> createVehiculo(@RequestBody Vehiculo vehiculo,
                                                   @RequestParam String permiso) {
        try {
            //Llama al servicio para crear el vehículo
            Vehiculo nuevoVehiculo = vehiculoService.createVehiculo(vehiculo, permiso);
            return new ResponseEntity<>(nuevoVehiculo, HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN); // Error de permisos
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // Otro error
        }
    }

    //Obtener un vehículo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> getVehiculoById(@PathVariable Long id, @RequestParam String permiso) {
        Vehiculo vehiculo = vehiculoService.getVehiculoById(id, permiso);
        if (vehiculo != null) {
            return ResponseEntity.ok(vehiculo);
        } else {
            return ResponseEntity.notFound().build(); //Retorna 404 si no se encuentra el vehículo
        }
    }

    //Obtener todos los vehículos
    @GetMapping
    public ResponseEntity<List<Vehiculo>> getAllVehiculos(@RequestParam String permiso) {
        List<Vehiculo> vehiculos = vehiculoService.getAllVehiculos(permiso);
        return ResponseEntity.ok(vehiculos);
    }

    //Actualizar un vehículo
    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> updateVehiculo(@PathVariable Long id,
                                                   @RequestBody Vehiculo vehiculo,
                                                   @RequestParam String permiso) {
        try {
            Vehiculo vehiculoActualizado = vehiculoService.updateVehiculo(id, vehiculo, permiso);
            if (vehiculoActualizado != null) {
                return ResponseEntity.ok(vehiculoActualizado);
            } else {
                return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra el vehículo
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra el vehículo
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN); // Error de permisos
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // Otro error
        }
    }

    //Eliminar un vehículo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehiculo(@PathVariable Long id, @RequestParam String permiso) {
        vehiculoService.deleteVehiculo(id, permiso);
        return ResponseEntity.noContent().build(); // Retorna 204 sin contenido
    }

    //Obtener vehículos disponibles en una fecha específica
    @GetMapping("/disponibles")
    public ResponseEntity<List<Vehiculo>> obtenerVehiculosDisponibles(@RequestParam LocalDate fecha, @RequestParam String permiso) {
        List<Vehiculo> vehiculosDisponibles = vehiculoService.obtenerVehiculosDisponibles(fecha, permiso);
        return ResponseEntity.ok(vehiculosDisponibles);
    }

    @PostMapping("/{id}/devolucion")
    public ResponseEntity<Informe> procesarArriendo(
            @PathVariable Long id,
            @RequestParam String observaciones) {

        Informe informe = vehiculoService.procesarArriendo(id, observaciones);
        return ResponseEntity.ok(informe);
    }

    @GetMapping("/{id}/informes")
    public ResponseEntity<String> obtenerInformesPorVehiculo(
            @PathVariable Long id,
            @RequestParam String permiso) {

        try {
            //Verificar permiso
            if (!permiso.equalsIgnoreCase("administrador")) {
                throw new IllegalStateException("Acceso denegado: Solo los administradores pueden realizar esta consulta.");
            }

            //Llamar al servicio para obtener los informes
            List<Informe> informes = vehiculoService.obtenerInformesPorVehiculo(id);
            return ResponseEntity.ok(informes.toString());

        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra el vehículo
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN); // Error de permisos
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); // Otro error
        }
    }


}
