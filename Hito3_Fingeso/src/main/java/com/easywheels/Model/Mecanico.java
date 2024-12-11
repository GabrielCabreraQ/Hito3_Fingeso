package com.easywheels.Model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
@DiscriminatorValue("mecanico")
public class Mecanico extends Usuario{

    // Atributos
    private String especialidad;
    private String departamento;

    // Constructor

    public Mecanico(long idUsuario, String nombreUsuario, String correoUsuario,
                    String contraseniaUsuario, String telefonoUsuario, Date fechaNacimiento,
                    String especialidad, String departamento) {
        super(idUsuario, nombreUsuario, correoUsuario, contraseniaUsuario,
                telefonoUsuario, fechaNacimiento);
        this.especialidad = especialidad;
        this.departamento = departamento;
    }

    public Mecanico(String especialidad, String departamento) {
        this.especialidad = especialidad;
        this.departamento = departamento;
    }

    public Mecanico() {}

    //Getters y Setters
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

}
