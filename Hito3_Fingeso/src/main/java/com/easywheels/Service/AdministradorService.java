package com.easywheels.Service;

import com.easywheels.Model.Administrador;
import com.easywheels.Model.Publicacion;
import com.easywheels.Model.Vehiculo;

import com.easywheels.Repository.AdministradorRepository;
import com.easywheels.Repository.PublicacionRepository;
import com.easywheels.Repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService {
    @Autowired // Inyectar dependencias automáticamente en Spring
    private AdministradorRepository administradorRepository; // Llamada al repo

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private VehiculoService vehiculoService; // variable para crear un vehiculo

    @Autowired
    private PublicacionService publicacionService; // variable para crear un vehiculo

    // CRUD

    // CREATE
    public Administrador crearAdministrador(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    //GET
    public List<Administrador> obtenerAdministradores() { // Cambiado a Administrador
        return administradorRepository.findAll();
    }

    public Optional<Administrador> obtenerAdministradorPorId(Long id) { // Cambiado a Administrador
        return administradorRepository.findById(id);
    }

    //PUT
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


    //Metodo publicar vehiculo
    public Vehiculo crearVehiculo(Vehiculo vehiculo, String permiso) {
        return vehiculoService.createVehiculo(vehiculo, permiso);
    }

    //Metodo para crear publicacion
    /**
     * Método para crear una publicación asociada a un vehículo
     *
     * @param idAdministrador ID del administrador que realiza la acción.
     * @param publicacion     Datos de la publicación a crear (vehículo incluido).
     * @return Publicación creada.
     */
    public Publicacion crearPublicacion(Long idAdministrador, Publicacion publicacion) {
        // Verificar si el administrador existe
        administradorRepository.findById(idAdministrador)
                .orElseThrow(() -> new IllegalArgumentException("Administrador no encontrado."));

        // Verificar si el vehículo asociado a la publicación existe
        Vehiculo vehiculo = vehiculoRepository.findById(publicacion.getVehiculo().getIdVehiculo())
                .orElseThrow(() -> new IllegalArgumentException("Vehículo especificado no encontrado."));

        // Validar si ya existe una publicación para el vehículo
        if (publicacionRepository.existsByVehiculo(vehiculo)) {
            throw new IllegalStateException("Ya existe una publicación asociada a este vehículo.");
        }

        // Asociar el vehículo a la publicación
        publicacion.setVehiculo(vehiculo);

        // Persistir la publicación
        return publicacionRepository.save(publicacion);
    }
}