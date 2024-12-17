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
import java.util.List;

@Service
public class ArrendatarioService {

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

    // Registrar usuario
    public Arrendatario register(String nombre, String correo, String contrasenia, String telefono, LocalDate fechaNacimiento, List<String> tipoLicencia, List<String> documentos) {
        Arrendatario nuevoUsuario = new Arrendatario(nombre, correo, contrasenia, telefono, fechaNacimiento, tipoLicencia, documentos);
        Arrendatario existente = arrendatarioRepository.findByCorreoUsuario(nuevoUsuario.getCorreoUsuario());
        if (existente != null) {
            return null;
        }
        return arrendatarioRepository.save(nuevoUsuario);
    }

    public boolean verificarDisponibilidad(Vehiculo vehiculo, LocalDate fechaInicio, LocalDate fechaFinal) {
        // Consultar en la base de datos los arriendos para este vehículo
        List<Arriendo> arriendos = arriendoRepository.findByVehiculoId(vehiculo.getIdVehiculo());

        // Verificar si hay conflictos de fechas
        for (Arriendo arriendo : arriendos) {
            LocalDate inicioArriendo = arriendo.getFechaInicio();
            LocalDate finArriendo = arriendo.getFechaFin();

            // Si hay cualquier intersección entre el rango solicitado y un arriendo existente, no está disponible
            if (!(fechaFinal.isBefore(inicioArriendo) || fechaInicio.isAfter(finArriendo))) {
                return false;
            }
        }

        // Si no hay conflictos, el vehículo está disponible
        return true;
    }

    public Arriendo arrendarVehiculo(long idArrendatario, long idPublicacion, LocalDate fechaInicio, LocalDate fechaFinal) {
        Arrendatario arrendatario = arrendatarioRepository.findById(idArrendatario)
                .orElseThrow(() -> new IllegalArgumentException("Arrendatario no encontrado."));

        Publicacion publicacion = publicacionRepository.findById(idPublicacion)
                .orElseThrow(() -> new IllegalArgumentException("Publicación no encontrada."));

        Vehiculo vehiculo = publicacion.getVehiculo();

        // Validar fechas
        if (fechaInicio.isAfter(fechaFinal)) {
            throw new IllegalArgumentException("La fecha de inicio debe ser anterior a la fecha final.");
        }

        // Verificar disponibilidad del vehículo
        if (!verificarDisponibilidad(vehiculo, fechaInicio, fechaFinal)) {
            throw new IllegalStateException("El vehículo no está disponible en las fechas seleccionadas.");
        }

        // Calcular el precio total (por número de días)
        long dias = fechaFinal.toEpochDay() - fechaInicio.toEpochDay();
        int precioTotal = (int) (dias * publicacion.getPrecioNormal());

        // Crear el arriendo (sin configurar el ID)
        Arriendo arriendo = new Arriendo();
        arriendo.setCancelado(true);
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
        nuevaBoleta.setFechaPago(LocalDateTime.now().withNano(0));  // Establecer la hora exacta de pago
        nuevaBoleta.setMontoTotal(precioTotal);

        arriendo.setBoleta(nuevaBoleta);

        // Guardar la boleta y el arriendo con su boleta asociada
        boletaRepository.save(nuevaBoleta);
        arriendoRepository.save(arriendo);

        // Cambiar el estado del vehículo a "no devuelto"
        vehiculo.setDevuelto(false);
        vehiculoRepository.save(vehiculo);

        return arriendo;
    }
}
