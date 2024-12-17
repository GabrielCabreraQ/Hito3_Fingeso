package com.easywheels.Controller;

import com.easywheels.Model.Publicacion;
import com.easywheels.Service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publicaciones")
@CrossOrigin
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    //Crear una nueva publicación
    @PostMapping
    public ResponseEntity<?> createPublicacion(
            @RequestBody Publicacion publicacion,
            @RequestParam String permiso) {
        try {
            //Llama al servicio para crear la publicación
            Publicacion nuevaPublicacion = publicacionService.createPublicacion(publicacion, permiso);
            return new ResponseEntity<>(nuevaPublicacion, HttpStatus.CREATED); // Retorna la publicación creada
        } catch (IllegalStateException e) {
            //Si hay un problema con los permisos
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (RuntimeException e) {
            //Si ocurre un error (e.g., el vehículo o catálogo no existe)
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            //Para cualquier otro error inesperado
            return new ResponseEntity<>("Error inesperado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //Obtener una publicación por ID
    @GetMapping("/{id}")
    public Publicacion getPublicacionById(@PathVariable int id) {
        return publicacionService.getPublicacionById(id);
    }

    //Obtener todas las publicaciones
    @GetMapping
    public List<Publicacion> getAllPublicaciones() {
        return publicacionService.getAllPublicaciones();
    }

    @GetMapping("/visibles")
    public ResponseEntity<List<Publicacion>> obtenerPublicacionesVisibles() {
        List<Publicacion> publicacionesVisibles = publicacionService.obtenerPublicacionesVisibles();
        return ResponseEntity.ok(publicacionesVisibles);
    }

    //Actualizar una publicación existente
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePublicacion(@PathVariable int id, @RequestBody Publicacion publicacion, @RequestParam String permiso) {
        publicacion.setIdPublicacion((long) id); //Asegurar que el ID coincida con el de la ruta
        try {
            Publicacion updatedPublicacion = publicacionService.updatePublicacion(publicacion, permiso);
            return new ResponseEntity<>(updatedPublicacion, HttpStatus.OK); //Retorna la publicación actualizada
        } catch (IllegalStateException e) {
            //Manejo de error de permisos
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        } catch (IllegalArgumentException e) {
            //Manejo de error si la publicación no existe
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            //Manejo de error general
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            //Para cualquier otro error inesperado
            return new ResponseEntity<>("Error inesperado: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //Eliminar una publicación
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
