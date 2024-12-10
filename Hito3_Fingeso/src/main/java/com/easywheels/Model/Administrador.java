package com.easywheels.Model;

import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class Administrador extends Usuario {

    //Atributos
    private String departamento;

    //Constructor
    public Administrador(long idUsuario, String nombreUsuario, String correoUsuario,
                         String contraseniaUsuario, String telefonoUsuario,
                         Date fechaNacimiento, String rol, String departamento) {
        super(idUsuario, nombreUsuario, correoUsuario, contraseniaUsuario,
                telefonoUsuario, fechaNacimiento, rol);
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
