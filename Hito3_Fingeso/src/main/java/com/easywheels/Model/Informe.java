package com.easywheels.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Informe {

    // Atributos
    private Vehiculo vehiculoMasArrendado;
    private String tipovehiculoMasArrendado;
    private String localidadVehiculoMasArrendado;


}