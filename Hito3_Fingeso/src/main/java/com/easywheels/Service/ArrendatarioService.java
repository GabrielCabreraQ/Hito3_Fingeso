package com.easywheels.Service;

import com.easywheels.Model.Arrendatario;
import com.easywheels.Repository.ArrendatarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArrendatarioService {
    @Autowired
    private ArrendatarioRepository arrendatarioRepositorio;

    //registrar usuario
    public Arrendatario register(String nombre, String correo, String contrasenia, String telefono, Date fechaNacimiento, List<String> tipoLicencia, List<String> documentos){
        Arrendatario nuevoUsuario = new Arrendatario(nombre, correo, contrasenia, telefono, fechaNacimiento, tipoLicencia, documentos);
        Arrendatario existente = arrendatarioRepositorio.findByCorreoUsuario(nuevoUsuario.getCorreoUsuario());
        if(existente != null){
            return null;
        }
        return arrendatarioRepositorio.save(nuevoUsuario);
    }
}
