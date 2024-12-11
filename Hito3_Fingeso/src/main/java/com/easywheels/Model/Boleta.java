package com.easywheels.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Boleta {                   // Falta relacionar con la base de datos

    // Atributos
    private Date fechaPago;
    private int montoTotal;
    private String estado;
    private String metodoDePago;

}
