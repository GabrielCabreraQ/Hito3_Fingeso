package com.easywheels.Model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
@DiscriminatorValue("administrador")    // Valor que se asigna al atributo tipo_usuario
public class Administrador extends Usuario {

    //Atributos
    private String departamento;

    //Constructor
    public Administrador(long idUsuario, String nombreUsuario, String correoUsuario,
                         String contraseniaUsuario, String telefonoUsuario,
                         Date fechaNacimiento, String departamento) {
        super(idUsuario, nombreUsuario, correoUsuario, contraseniaUsuario,
                telefonoUsuario, fechaNacimiento);
        this.departamento = departamento;
    }

    public Administrador(String departamento) {
        this.departamento = departamento;
    }

    public Administrador() {}


    // Getters y Setters
    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
