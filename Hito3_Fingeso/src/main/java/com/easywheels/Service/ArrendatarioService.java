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
    public Arrendatario register(String nombre, String correo, String contrasenia, String telefono, Date fechaNacimiento, List<String> tipoLicencia, List<String> documentos){
        Arrendatario nuevoUsuario = new Arrendatario(nombre, correo, contrasenia, telefono, fechaNacimiento, tipoLicencia, documentos);
        Arrendatario existente = arrendatarioRepository.findByCorreoUsuario(nuevoUsuario.getCorreoUsuario());
        if(existente != null){
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

        List<LocalDate> fechasAEliminar = new ArrayList<>();
        for (LocalDate disponibilidad : vehiculo.getDisponibilidad()) {
            // Comparar las fechas correctamente
            if (!disponibilidad.isBefore(inicio) && !disponibilidad.isAfter(fin)) {
                fechasAEliminar.add(disponibilidad);
            }
        }

        // Remover las fechas no disponibles y guardar cambios
        vehiculo.getDisponibilidad().removeAll(fechasAEliminar);
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

        // Actualizar la disponibilidad del vehículo
        actualizarDisponibilidad(vehiculo, fechaInicio, fechaFinal);

        return arriendo;
    }



    public void pagarArriendo(long idArrendatario, int idArriendo) {
        Arrendatario arrendatario = arrendatarioRepository.findById(idArrendatario)
                .orElseThrow(() -> new IllegalArgumentException("Arrendatario no encontrado."));

        Arriendo arriendo = arriendoRepository.findById(idArriendo)
                .orElseThrow(() -> new IllegalArgumentException("Arriendo no encontrado."));

        if (arriendo.getCancelado()) {
            throw new IllegalStateException("El arriendo ya ha sido pagado.");
        }

        // Simular procesamiento de pago
        boolean pagoExitoso = true; // Cambiar por lógica real de pago
        if (!pagoExitoso) {
            throw new RuntimeException("El pago no se pudo completar.");
        }

        // Actualizar el estado del arriendo
        arriendo.setCancelado(true);

        // Crear y asociar la boleta (cambia el nombre de la variable local)
        Boleta nuevaBoleta = new Boleta(); // Asegúrate de agregar los detalles necesarios
        arriendo.setBoleta(nuevaBoleta);

        // Guardar la boleta y el arriendo
        boletaRepository.save(nuevaBoleta); // Guardar la boleta
        arriendoRepository.save(arriendo);   // Guardar el arriendo actualizado
    }

}
