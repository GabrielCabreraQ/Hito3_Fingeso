package com.easywheels.Service;

import com.easywheels.Model.Vehiculo;
import com.easywheels.Model.Publicacion;
import com.easywheels.Repository.PublicacionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicacionService {
    @Autowired       // Inyectar dependencias automaticaente en Spring
    private PublicacionRepository publicacionRepository;      // Llamada al repo

    @Autowired
    private VehiculoService VehiculoService;

    @Autowired
    private CatalogoService CatalogoService;

    // Metodo para verificar el permiso
    private void verificarPermisosAdmin(String permiso) {
        if (!"administrador".equals(permiso)) {
            throw new IllegalStateException("No tienes permisos para esta acción.");
        }
    }

    //Crear publicación
    public Publicacion createPublicacion(Publicacion publicacion, String permiso) {
        // Validar y obtener el vehículo
        Vehiculo vehiculo = VehiculoService.getVehiculoById(publicacion.getVehiculo().getIdVehiculo(), permiso);

        // Asociar entidades
        publicacion.setVehiculo(vehiculo);

        //Guardar la publicación
        return publicacionRepository.save(publicacion);
    }


    //Obtener por id una publicación
    public Publicacion getPublicacionById(int id) {
        return publicacionRepository.findById((long) id).orElse(null);
    }

    public List<Publicacion> getAllPublicaciones() {
        return publicacionRepository.findAll();
    }

    // Método para obtener todas las publicaciones con visibilidad en true
    public List<Publicacion> obtenerPublicacionesVisibles() {
        return publicacionRepository.findByVisibilidadTrue();
    }

    //Actualizar datos de una publicación
    public Publicacion updatePublicacion(Publicacion publicacion, String permiso) {
        //Verificar permisos de administrador antes de actualizar
        verificarPermisosAdmin(permiso);

        //Verificar si la publicación existe
        if (!publicacionRepository.existsById(publicacion.getIdPublicacion())) {
            throw new IllegalArgumentException("La publicación con ID " + publicacion.getIdPublicacion() + " no existe.");
        }

        //Obtener la publicación existente desde la base de datos
        Publicacion existingPublicacion = publicacionRepository.findById(publicacion.getIdPublicacion()).orElseThrow(() ->
                new IllegalArgumentException("La publicación no existe.")
        );

        //Mantener el ID del catálogo existente si no se proporciona uno nuevo
        if (existingPublicacion.getCatalogo() != null && publicacion.getCatalogo() == null) {
            publicacion.setCatalogo(existingPublicacion.getCatalogo());
        }

        //Actualizar otros campos
        existingPublicacion.setPrecioNormal(publicacion.getPrecioNormal());
        existingPublicacion.setCodigoACRISS(publicacion.getCodigoACRISS());
        existingPublicacion.setVisibilidad(publicacion.getVisibilidad());
        existingPublicacion.setVehiculo(publicacion.getVehiculo());

        //No se modifica el ID del catálogo si ya tiene uno y no viene en el JSON
        existingPublicacion.setCatalogo(publicacion.getCatalogo());

        //Guardar y retornar la publicación actualizada
        return publicacionRepository.save(existingPublicacion);
    }


    //Eliminar una publicación de la base de datos
    public boolean deletePublicacion(int id, String permiso) {
        verificarPermisosAdmin(permiso);
        publicacionRepository.deleteById((long) id);
        return false;
    }

    //Metodo para visualizar un vehículo asociado a una publicación
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
                //", Disponibilidad: " + (vehiculo.getDisponibilidad() != null ? vehiculo.getDisponibilidad() : "No especificada") +
                ", Precio: " + publicacion.getPrecioNormal();
    }

}
