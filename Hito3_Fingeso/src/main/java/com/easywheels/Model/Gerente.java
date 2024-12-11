package com.easywheels.Model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@DiscriminatorValue("gerente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gerente extends Usuario {

    // Atributos
    private String departamento;

}
