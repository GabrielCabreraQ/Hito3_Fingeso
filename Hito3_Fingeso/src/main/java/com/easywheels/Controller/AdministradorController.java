package com.easywheels.Controller;

import com.easywheels.Model.Administrador;
import com.easywheels.Model.Publicacion;
import com.easywheels.Model.Vehiculo;

import com.easywheels.Service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administradores")
@CrossOrigin
public class AdministradorController {
    @Autowired
    private AdministradorService administradorService;

    //Controlador para crear un administrador
    @PostMapping
    public Administrador crearAdministrador(@RequestBody Administrador administrador) {
        return administradorService.crearAdministrador(administrador);
    }

    //Controlador para obtener a todos los administradores
    @GetMapping
    public List<Administrador> obtenerAdministradores() {
        return administradorService.obtenerAdministradores();
    }

    //Controlador para obtener a un administrador por su id
    @GetMapping("/{id}")
    public ResponseEntity<Administrador> obtenerAdministradorPorId(@PathVariable Long id) {
        return administradorService.obtenerAdministradorPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Controlador para actualizar datos de un administrador
    @PutMapping("/{id}")
    public ResponseEntity<Administrador> actualizarAdministrador(@PathVariable Long id, @RequestBody Administrador administrador) {
        Administrador updatedAdministrador = administradorService.actualizarAdministrador(id, administrador);
        return updatedAdministrador != null ? ResponseEntity.ok(updatedAdministrador) : ResponseEntity.notFound().build();
    }

    //Controlador para eliminar un administrador de la DB dado su id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAdministrador(@PathVariable Long id) {
        administradorService.eliminarAdministrador(id);
        return ResponseEntity.noContent().build();
    }

    //Controlador para crear un vehiculo
    @PostMapping("/createVehiculo")
    public ResponseEntity<Vehiculo> crearVehiculo(@RequestBody Vehiculo vehiculo) {
        // Aquí, el permiso podría ser obtenido de la sesión del usuario autenticado o ser parte del objeto Administrador
        String permiso = "administrador"; // Cambia esto por la lógica de permisos adecuada

        // Crear vehiculo a través del servicio
        Vehiculo nuevoVehiculo = administradorService.crearVehiculo(vehiculo, permiso);
        return ResponseEntity.ok(nuevoVehiculo);
    }

    //Controlador para crear una publicacion
    @PostMapping("/createPublicacion")
    public ResponseEntity<Publicacion> crearPublicacion(@RequestBody Publicacion publicacion) {
        String permiso = "administrador";

        //Crear publicacion a través del servicio
        Publicacion nuevaPublicacion = administradorService.crearPublicacion(publicacion, permiso);
        return ResponseEntity.ok(nuevaPublicacion);
    }

    //Controlador para visualizar una publicacion
    @GetMapping("/{id}/visualizarPublicacion")
    public ResponseEntity<String> visualizarPublicacion(@PathVariable int id) {
        String info = administradorService.visualizarPublicacion(id);
        return ResponseEntity.ok(info);
    }

    // Controlador para eliminar una publicación
    @DeleteMapping("/deletePublicacion/{id}")
    public ResponseEntity<Void> eliminarPublicacion(@PathVariable int id) {
        String permiso = "administrador";
        boolean eliminado = administradorService.eliminarPublicacion(id, permiso);
        return eliminado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


    //Controlador para actualizar una publicacion
    @PutMapping("/{id}/updatePublicacion")
    public ResponseEntity<Publicacion> updatePublicacion(@PathVariable int id, @RequestBody Publicacion publicacion) {
        // Aquí, el permiso podría ser obtenido de la sesión del usuario autenticado o ser parte del objeto Administrador
        String permiso = "administrador"; // Cambia esto por la lógica de permisos adecuada

        Publicacion updatedPublicacion = administradorService.updatePublicacion(id, publicacion, permiso);
        return updatedPublicacion != null ? ResponseEntity.ok(updatedPublicacion) : ResponseEntity.notFound().build();
    }


}
