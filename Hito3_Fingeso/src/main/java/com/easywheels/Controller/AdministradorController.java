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

    @PostMapping
    public Administrador crearAdministrador(@RequestBody Administrador administrador) {
        return administradorService.crearAdministrador(administrador);
    }

    @GetMapping
    public List<Administrador> obtenerAdministradores() {
        return administradorService.obtenerAdministradores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Administrador> obtenerAdministradorPorId(@PathVariable Long id) {
        return administradorService.obtenerAdministradorPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Administrador> actualizarAdministrador(@PathVariable Long id, @RequestBody Administrador administrador) {
        Administrador updatedAdministrador = administradorService.actualizarAdministrador(id, administrador);
        return updatedAdministrador != null ? ResponseEntity.ok(updatedAdministrador) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAdministrador(@PathVariable Long id) {
        administradorService.eliminarAdministrador(id);
        return ResponseEntity.noContent().build();
    }
}
