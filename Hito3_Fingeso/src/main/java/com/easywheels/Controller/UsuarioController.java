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
@CrossOrigin
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

    // Retorna el objeto Usuario si el usuario esta registrado en la bae de datos. Se indica el tipo de usuario.
    @PostMapping("/login")
    public ResponseEntity<Usuario> loginByUsuario(@RequestParam String correo, @RequestParam String password) {
        try {
            Usuario existeUsuario = usuarioService.obtenerUsuarioByCorreoContrasenia(correo, password);
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
