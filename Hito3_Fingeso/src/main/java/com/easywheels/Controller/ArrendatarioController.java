package com.easywheels.Controller;

import com.easywheels.Model.Arrendatario;
import com.easywheels.Service.ArrendatarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/arrendatario")
public class ArrendatarioController {
    @Autowired
    private ArrendatarioService arrendatarioService;

    //registrar usuario
    @PostMapping("/register")
    public Arrendatario register(@RequestBody Arrendatario nuevo) {
        return arrendatarioService.register(nuevo.getNombreUsuario(), nuevo.getCorreoUsuario(), nuevo.getContraseniaUsuario(), nuevo.getTelefonoUsuario(), nuevo.getFechaNacimiento(), nuevo.getTipoLicencia(), nuevo.getDocumentos());
    }
}
