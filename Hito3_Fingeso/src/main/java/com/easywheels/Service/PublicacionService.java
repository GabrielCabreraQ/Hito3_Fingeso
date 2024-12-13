package com.easywheels.Service;

import com.easywheels.Repository.PublicacionRepository;
import com.easywheels.Model.Publicacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicacionService {
    @Autowired       // Inyectar dependencias automaticaente en Spring
    private PublicacionRepository publicacionRepository;      // Llamada al repo

    // Método para verificar el permiso
    private void verificarPermisosAdmin(String permiso) {
        if (!"administrador".equals(permiso)) {
            throw new IllegalStateException("No tienes permisos para esta acción.");
        }
    }

    // CRUD con validación de permisos
    // Create
    public Publicacion createPublicacion(Publicacion publicacion, String permiso) {
        verificarPermisosAdmin(permiso);
        return publicacionRepository.save(publicacion);
    }


    // Read
    public Publicacion getPublicacionById(int id, String permiso) {
        verificarPermisosAdmin(permiso);
        return publicacionRepository.findById((long) id).orElse(null);
    }


    public List<Publicacion> getAllPublicaciones(String permiso) {
        verificarPermisosAdmin(permiso);
        return publicacionRepository.findAll();
    }

    // Update
    public Publicacion updatePublicacion(Publicacion publicacion, String permiso) {
        verificarPermisosAdmin(permiso);
        return publicacionRepository.save(publicacion);
    }

    // Delete
    public void deletePublicacion(int id, String permiso) {
        verificarPermisosAdmin(permiso);
        publicacionRepository.deleteById((long) id);
    }

}
