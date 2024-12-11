package com.easywheels.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity     // Indicamos a Spring que es un objeto entidad para la tabla vehiculo
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehiculo {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // Genera Id automatico
    private Long idVehiculo;

    private String marca;
    private String modelo;
    //private String color;             Este atributo no esstaba en el diagrama de clases
    private int anio;
    private String tipoTransmision;
    private String categoria;
    private String tipoCuerpo;
    private String combustibleAC;

    @ElementCollection          // Esto es asi?????? muchas preguntas pocas respuestas
    private List<Date> disponibilidad;

    private Boolean devuelto;




}
