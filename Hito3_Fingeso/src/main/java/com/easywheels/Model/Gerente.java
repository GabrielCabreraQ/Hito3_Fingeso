package com.easywheels.Model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
@DiscriminatorValue("gerente")

public class Gerente extends Usuario {

    //Atributos
    private String departamento;

}
