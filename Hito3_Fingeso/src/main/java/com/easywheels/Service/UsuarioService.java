package com.easywheels.Service;

import com.easywheels.Model.Usuario;
import com.easywheels.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    //Obtener todos los usuarios, El gerente tiene la potestad de obtener todos los usuarios                   -- Creo que esta se debe mover a generenteService
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    // Obtner usuario segun correo y contrasenia
    public Usuario obtenerUsuarioByCorreoContrasenia(String correo, String password) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreoUsuario(correo);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (usuario.getContraseniaUsuario().equals(password)) {
                return usuario;
            }
        }
        throw new RuntimeException("Credenciales incorrectas");
    }

    //Obtener 1 si usuario existe en la base de datos, y 0 si no existe
    public int login(String correo, String password) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreoUsuario(correo);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            if (usuario.getContraseniaUsuario().equals(password)) {
                return 1;
            }
        }
        return 0;
    }
}
