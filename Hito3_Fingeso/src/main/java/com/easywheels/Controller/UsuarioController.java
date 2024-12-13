package com.easywheels.Controller;

import com.easywheels.Model.Administrador;
import com.easywheels.Model.Usuario;
import com.easywheels.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/getUserBy")
    public ResponseEntity<Usuario> obtenerUsuarioByCorreoContrasenia(@RequestParam String correo, @RequestParam String password) {
        try {
            Usuario usuario = usuarioService.obtenerUsuarioByCorreoContrasenia(correo, password);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(null);
        }
    }

    // Retorna 1 si encuentra al usuario en la base de datos, y 0 si no lo hace
    @PostMapping("/login")
    public ResponseEntity<Integer> login(@RequestParam String correo, @RequestParam String password) {
        try {
            Integer existeUsuario = usuarioService.login(correo, password);
            return ResponseEntity.ok(existeUsuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(null);
        }
    }

    @GetMapping
    public List<Usuario> listarTodosLosUsuarios() {
        return usuarioService.obtenerTodosLosUsuarios();
    }

}
