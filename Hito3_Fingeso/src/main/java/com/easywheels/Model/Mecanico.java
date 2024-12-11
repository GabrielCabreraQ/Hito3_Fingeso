package com.easywheels.Model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@DiscriminatorValue("mecanico")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mecanico extends Usuario{

    // Atributos
    private String especialidad;
    private String departamento;


}
