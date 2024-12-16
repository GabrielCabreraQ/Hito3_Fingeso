package com.easywheels.Controller;

import com.easywheels.Model.Arrendatario;
import com.easywheels.Model.Arriendo;
import com.easywheels.Service.ArrendatarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/arrendatarios")
public class ArrendatarioController {
    @Autowired
    private ArrendatarioService arrendatarioService;

    //Registrar usuario
    @PostMapping("/register")
    public ResponseEntity<Arrendatario> register(@RequestBody Arrendatario nuevo) {
        try {
            // Llamar al servicio para registrar al nuevo arrendatario
            Arrendatario registrado = arrendatarioService.register(nuevo.getNombreUsuario(), nuevo.getCorreoUsuario(),
                    nuevo.getContraseniaUsuario(), nuevo.getTelefonoUsuario(),
                    nuevo.getFechaNacimiento(), nuevo.getTipoLicencia(), nuevo.getDocumentos());
            return new ResponseEntity<>(registrado, HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN); // Error de permisos o estado no válido
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);  // Otro error
        }
    }

    //Arrendar un vehículo
    @PostMapping("/{idArrendatario}/arrendar")
    public ResponseEntity<Arriendo> arrendarVehiculo(
            @PathVariable long idArrendatario,
            @RequestBody Map<String, Object> body) {  // Recibe el JSON como Map

        try {
            //Parsear las fechas de inicio y fin (asumiendo que vienen como cadenas en el cuerpo de la solicitud)
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  //Cambiar el formato si es necesario
            Date fechaInicio = dateFormat.parse((String) body.get("fechaInicio"));
            Date fechaFinal = dateFormat.parse((String) body.get("fechaFinal"));

            //Verificar que las fechas sean válidas
            if (fechaInicio == null || fechaFinal == null) {
                return ResponseEntity.badRequest().body(null);
            }

            //Obtener el idPublicacion del body
            long idPublicacion = ((Number) body.get("idPublicacion")).longValue();

            //Llamar al servicio para arrendar el vehículo
            Arriendo arriendo = arrendatarioService.arrendarVehiculo(idArrendatario, idPublicacion, fechaInicio, fechaFinal);
            return ResponseEntity.ok(arriendo);

        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);  //Error de permisos o estado no válido
        } catch (Exception e) {
            //Manejar excepciones de formato o problemas con las fechas
            return ResponseEntity.badRequest().body(null);
        }
    }

}
