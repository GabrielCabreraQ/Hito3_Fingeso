package com.easywheels.Service;

import com.easywheels.Model.Administrador;
import com.easywheels.Model.Publicacion;
import com.easywheels.Model.Vehiculo;

import com.easywheels.Repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService {
    @Autowired //Inyectar dependencias automáticamente en Spring
    private AdministradorRepository administradorRepository; //Llamada al repo

    @Autowired
    private VehiculoService vehiculoService; //variable para crear un vehiculo

    @Autowired
    private PublicacionService publicacionService; //variable para crear un vehiculo


    //Crea un administador
    public Administrador crearAdministrador(Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    //Obtener administradores
    public List<Administrador> obtenerAdministradores() { //Cambiado a Administrador
        return administradorRepository.findAll();
    }

    //Obtiene un administrador por ID
    public Optional<Administrador> obtenerAdministradorPorId(Long id) { //Cambiado a Administrador
        return administradorRepository.findById(id);
    }

    //Actualizar info de administrador
    public Administrador actualizarAdministrador(Long id, Administrador administrador) {
        if (administradorRepository.existsById(id)) {
            administrador.setIdUsuario(id);
            return administradorRepository.save(administrador);
        }
        return null; //o lanzar una excepción
    }

    //Eliminar un Administrador
    public void eliminarAdministrador(Long id) {
        administradorRepository.deleteById(id);
    }


    //Metodo publicar vehiculo
    public Vehiculo crearVehiculo(Vehiculo vehiculo, String permiso) {
        return vehiculoService.createVehiculo(vehiculo, permiso);
    }

    //Metodo para crear publicacion
    public Publicacion crearPublicacion(Publicacion publicacion, String permiso) {
        return publicacionService.createPublicacion(publicacion, permiso);
    }

    //Metodo para visualizar una publicacion
    public String visualizarPublicacion(int id) {
        Publicacion publicacion = publicacionService.getPublicacionById(id); // Asumimos que el ID es int en PublicacionService
        if (publicacion == null) {
            return "La publicación no existe.";
        }
        return publicacion.toString();
    }

    //Método para eliminar una publicación
    public boolean eliminarPublicacion(int id, String permiso) {
        return publicacionService.deletePublicacion(id, permiso);
    }

    //Metodo para actualizar o modificar una publicacion
    public Publicacion updatePublicacion(int id, Publicacion publicacion, String permiso) {
        // Establecer el ID de la publicación a actualizar
        publicacion.setIdPublicacion(id);
        // Llamar al método del servicio de publicación para actualizar
        return publicacionService.updatePublicacion(publicacion, permiso);
    }

}