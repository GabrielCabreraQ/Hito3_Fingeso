package com.easywheels.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolicitudCancelacion {

    // Atributos
    private Arrendatario arrendatario;
    private Arriendo arriendo;
    private String motivo;
    private Boolean aceptacion;

}
