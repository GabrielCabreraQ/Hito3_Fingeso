package com.easywheels.Service;

import com.easywheels.Model.Administrador; // Asegúrate de importar Administrador
import com.easywheels.Repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService {
    @Autowired // Inyectar dependencias automáticamente en Spring
    private AdministradorRepository administradorRepository; // Llamada al repo

    // CRUD

    // Crear un nuevo Administrador
    public Administrador crearAdministrador(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    // Obtener todos los Administradores
    public List<Administrador> obtenerAdministradores() { // Cambiado a Administrador
        return administradorRepository.findAll();
    }

    // Obtener un Administrador por ID
    public Optional<Administrador> obtenerAdministradorPorId(Long id) { // Cambiado a Administrador
        return administradorRepository.findById(id);
    }

    // Actualizar un Administrador
    public Administrador actualizarAdministrador(Long id, Administrador administrador) {
        if (administradorRepository.existsById(id)) {
            administrador.setIdUsuario(id);
            return administradorRepository.save(administrador);
        }
        return null; // o lanzar una excepción
    }

    // Eliminar un Administrador
    public void eliminarAdministrador(Long id) {
        administradorRepository.deleteById(id);
    }
}