package com.easywheels.Controller;

import com.easywheels.Model.Arrendatario;
import com.easywheels.Model.Arriendo;
import com.easywheels.Service.ArrendatarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;


@RestController
@RequestMapping("/arrendatarios")
@CrossOrigin
public class ArrendatarioController {

    @Autowired
    private ArrendatarioService arrendatarioService;

    // Registrar usuario
    @PostMapping("/register")
    public ResponseEntity<Arrendatario> register(@RequestBody Arrendatario nuevo) {
        try {
            Arrendatario registrado = arrendatarioService.register(
                    nuevo.getNombreUsuario(),
                    nuevo.getCorreoUsuario(),
                    nuevo.getContraseniaUsuario(),
                    nuevo.getTelefonoUsuario(),
                    nuevo.getFechaNacimiento(),
                    nuevo.getTipoLicencia(),
                    nuevo.getDocumentos()
            );
            return new ResponseEntity<>(registrado, HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Arrendar un vehículo
    @PostMapping("/{idArrendatario}/arrendar")
    public ResponseEntity<Arriendo> arrendarVehiculo(
            @PathVariable long idArrendatario,
            @RequestBody Map<String, Object> body) {

        try {
            // Parsear fechas desde el cuerpo de la solicitud
            String fechaInicioStr = (String) body.get("fechaInicio");
            String fechaFinalStr = (String) body.get("fechaFinal");

            LocalDate fechaInicio = LocalDate.parse(fechaInicioStr);
            LocalDate fechaFinal = LocalDate.parse(fechaFinalStr);

            if (fechaInicio == null || fechaFinal == null) {
                return ResponseEntity.badRequest().body(null);
            }

            long idPublicacion = ((Number) body.get("idPublicacion")).longValue();
            Arriendo arriendo = arrendatarioService.arrendarVehiculo(idArrendatario, idPublicacion, fechaInicio, fechaFinal);
            return ResponseEntity.ok(arriendo);

        } catch (DateTimeParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
