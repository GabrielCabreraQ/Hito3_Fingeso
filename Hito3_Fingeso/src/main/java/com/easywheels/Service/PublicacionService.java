package com.easywheels.Service;

import com.easywheels.Model.Vehiculo;
import com.easywheels.Repository.PublicacionRepository;
import com.easywheels.Model.Publicacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicacionService {
    @Autowired       // Inyectar dependencias automaticaente en Spring
    private PublicacionRepository publicacionRepository;      // Llamada al repo

    // Metodo para verificar el permiso
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
    public Publicacion getPublicacionById(int id) {
        return publicacionRepository.findById((long) id).orElse(null);
    }


    public List<Publicacion> getAllPublicaciones() {
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

    // Metodo para visualizar un vehículo asociado a una publicación
    public String visualizarVehiculo(Publicacion publicacion) {
        if (publicacion == null || publicacion.getVehiculo() == null) {
            return "No hay información del vehículo para esta publicación.";
        }
        if (!publicacion.getVisibilidad()) {
            return "La Publicación no está disponible.";
        }

        Vehiculo vehiculo = publicacion.getVehiculo();
        return "Vehículo: " + vehiculo.getMarca() + " " + vehiculo.getModelo() +
                ", Año: " + vehiculo.getAnio() +
                ", Tipo de Transmisión: " + vehiculo.getTipoTransmision() +
                ", Categoría: " + vehiculo.getCategoria() +
                ", Tipo de Cuerpo: " + vehiculo.getTipoCuerpo() +
                ", Combustible/AC: " + vehiculo.getCombustibleAC() +
                ", Disponibilidad: " + (vehiculo.getDisponibilidad() != null ? vehiculo.getDisponibilidad() : "No especificada") +
                ", Precio: " + publicacion.getPrecioNormal();
    }


}
