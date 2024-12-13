package com.easywheels.Controller;

import com.easywheels.Model.Publicacion;
import com.easywheels.Service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    // Crear una nueva publicación
    @PostMapping
    public Publicacion createPublicacion(@RequestBody Publicacion publicacion, @RequestParam String permiso) {
        return publicacionService.createPublicacion(publicacion, permiso);
    }

    // Obtener una publicación por ID
    @GetMapping("/{id}")
    public Publicacion getPublicacionById(@PathVariable int id) {
        return publicacionService.getPublicacionById(id);
    }

    // Obtener todas las publicaciones
    @GetMapping
    public List<Publicacion> getAllPublicaciones() {
        return publicacionService.getAllPublicaciones();
    }

    // Actualizar una publicación existente
    @PutMapping("/{id}")
    public Publicacion updatePublicacion(@PathVariable int id, @RequestBody Publicacion publicacion, @RequestParam String permiso) {
        publicacion.setIdPublicacion(id); // Asegurar que el ID coincida con el de la ruta
        return publicacionService.updatePublicacion(publicacion, permiso);
    }

    // Eliminar una publicación
    @DeleteMapping("/{id}")
    public void deletePublicacion(@PathVariable int id, @RequestParam String permiso) {
        publicacionService.deletePublicacion(id, permiso);
    }

    // Visualizar información de un vehículo asociado a una publicación
    @GetMapping("/{id}/visualizarVehiculo")
    public String visualizarVehiculo(@PathVariable int id) {
        Publicacion publicacion = publicacionService.getPublicacionById(id);
        return publicacionService.visualizarVehiculo(publicacion);
    }
}
