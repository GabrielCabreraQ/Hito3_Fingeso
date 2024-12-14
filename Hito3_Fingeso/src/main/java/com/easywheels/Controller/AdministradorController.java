package com.easywheels.Controller;

import com.easywheels.Model.Administrador;
import com.easywheels.Service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administradores")
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
}
