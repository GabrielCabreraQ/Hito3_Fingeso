package com.easywheels.Model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@DiscriminatorValue("administrador")    // Valor que se asigna al atributo tipo_usuario
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administrador extends Usuario {

    //Atributos
    private String departamento;


}
