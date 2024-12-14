package com.easywheels.Service;

import com.easywheels.Model.*;
import com.easywheels.Repository.ArrendatarioRepository;
import com.easywheels.Repository.ArriendoRepository;
import com.easywheels.Repository.PublicacionRepository;
import com.easywheels.Repository.VehiculoRepository;
import com.easywheels.Repository.BoletaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.ZoneId;

@Service
public class ArrendatarioService {

    public static LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @Autowired
    private ArrendatarioRepository arrendatarioRepository; // Este es el repositorio para acceder a los arrendatarios

    @Autowired
    private PublicacionRepository publicacionRepository;

    @Autowired
    private ArriendoRepository arriendoRepository;

    @Autowired
    private BoletaRepository boletaRepository;  // Inyección del repositorio Boleta

    @Autowired
    private VehiculoRepository vehiculoRepository;

    //registrar usuario
    public Arrendatario register(String nombre, String correo, String contrasenia, String telefono, Date fechaNacimiento, List<String> tipoLicencia, List<String> documentos) {
        Arrendatario nuevoUsuario = new Arrendatario(nombre, correo, contrasenia, telefono, fechaNacimiento, tipoLicencia, documentos);
        Arrendatario existente = arrendatarioRepository.findByCorreoUsuario(nuevoUsuario.getCorreoUsuario());
        if (existente != null) {
            return null;
        }
        return arrendatarioRepository.save(nuevoUsuario);
    }

    public boolean verificarDisponibilidad(Vehiculo vehiculo, Date fechaInicio, Date fechaFinal) {
        LocalDate inicio = convertToLocalDate(fechaInicio);
        LocalDate fin = convertToLocalDate(fechaFinal);

        for (LocalDate disponibilidad : vehiculo.getDisponibilidad()) {
            if (!disponibilidad.isBefore(inicio) && !disponibilidad.isAfter(fin)) {
                return false; // El vehículo no está disponible
            }
        }
        return true;
    }

    public void actualizarDisponibilidad(Vehiculo vehiculo, Date fechaInicio, Date fechaFinal) {
        // Convertir las fechas de inicio y final a LocalDate
        LocalDate inicio = fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fin = fechaFinal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Eliminar las fechas de disponibilidad dentro del rango del arrendamiento
        List<LocalDate> fechasAEliminar = new ArrayList<>();
        for (LocalDate disponibilidad : vehiculo.getDisponibilidad()) {
            if (!disponibilidad.isBefore(inicio) && !disponibilidad.isAfter(fin)) {
                fechasAEliminar.add(disponibilidad);
            }
        }
        // Remover las fechas no disponibles
        vehiculo.getDisponibilidad().removeAll(fechasAEliminar);

        // Agregar la nueva fecha de disponibilidad después del arriendo (día siguiente al fin del arriendo)
        vehiculo.getDisponibilidad().add(fin.plusDays(1));

        // Guardar los cambios en el vehículo
        vehiculoRepository.save(vehiculo);
    }

    public Arriendo arrendarVehiculo(long idArrendatario, long idPublicacion, Date fechaInicio, Date fechaFinal) {
        Arrendatario arrendatario = arrendatarioRepository.findById(idArrendatario)
                .orElseThrow(() -> new IllegalArgumentException("Arrendatario no encontrado."));

        Publicacion publicacion = publicacionRepository.findById(idPublicacion)
                .orElseThrow(() -> new IllegalArgumentException("Publicación no encontrada."));

        Vehiculo vehiculo = publicacion.getVehiculo();

        // Validar fechas
        if (fechaInicio.after(fechaFinal)) {
            throw new IllegalArgumentException("La fecha de inicio debe ser anterior a la fecha final.");
        }

        // Verificar disponibilidad del vehículo
        if (!verificarDisponibilidad(vehiculo, fechaInicio, fechaFinal)) {
            throw new IllegalStateException("El vehículo no está disponible en las fechas seleccionadas.");
        }

        // Calcular el precio total (por número de días)
        long dias = (fechaFinal.getTime() - fechaInicio.getTime()) / (1000 * 60 * 60 * 24);
        int precioTotal = (int) (dias * publicacion.getPrecioNormal());

        // Crear el arriendo (sin configurar el ID)
        Arriendo arriendo = new Arriendo();
        arriendo.setCancelado(false);
        arriendo.setConformidad(false);
        arriendo.setPrecio(precioTotal);
        arriendo.setFechaInicio(fechaInicio);
        arriendo.setFechaFin(fechaFinal);
        arriendo.setArrendatario(arrendatario);
        arriendo.setVehiculo(vehiculo);

        try {
            // Guardar el arriendo en la base de datos
            arriendoRepository.save(arriendo);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalStateException("Error al crear el arriendo: probablemente un ID duplicado.", e);
        }

        // Crear la boleta asociada al arriendo y marcarla como pagada
        Boleta nuevaBoleta = new Boleta();
        nuevaBoleta.setEstado("Pagado");  // Marcar la boleta como pagada
        nuevaBoleta.setFechaPago(LocalDateTime.now());  // Establecer la hora exacta de pago

        arriendo.setBoleta(nuevaBoleta);

        // Guardar la boleta y el arriendo con su boleta asociada
        boletaRepository.save(nuevaBoleta);
        arriendoRepository.save(arriendo);

        // Actualizar la disponibilidad del vehículo con la fecha final del arriendo
        actualizarDisponibilidad(vehiculo, fechaInicio, fechaFinal);

        // Cambiar el estado del vehículo a "no devuelto"
        vehiculo.setDevuelto(false);
        vehiculoRepository.save(vehiculo);

        return arriendo;
    }

}
