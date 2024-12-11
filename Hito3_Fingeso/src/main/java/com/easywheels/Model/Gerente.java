package com.easywheels.Model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
@DiscriminatorValue("gerente")
public class Gerente extends Usuario {

    // Atributos
    private String departamento;

    // Constructor

    public Gerente(long idUsuario, String nombreUsuario, String correoUsuario,
                   String contraseniaUsuario, String telefonoUsuario, Date fechaNacimiento,
                   String departamento) {
        super(idUsuario, nombreUsuario, correoUsuario, contraseniaUsuario,
                telefonoUsuario, fechaNacimiento);
        this.departamento = departamento;
    }

    public Gerente(String departamento) {
        this.departamento = departamento;
    }

    public Gerente() {}


    // Getters y Setters
    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

}
