package com.easywheels.Model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("administrador")    // Valor que se asigna al atributo tipo_usuario
public class Administrador extends Usuario {

    // Atributos
    private String departamento;

    // Constructor sin ID
    public Administrador(String nombreUsuario, String correoUsuario, String contraseniaUsuario, String telefonoUsuario,
                         LocalDate fechaNacimiento, List<Notificacion> notificaciones, String departamento) {
        super(nombreUsuario, correoUsuario, contraseniaUsuario, telefonoUsuario, fechaNacimiento, notificaciones);
        this.departamento = departamento;
    }

    // Constructor con solo departamento
    public Administrador(String departamento) {
        this.departamento = departamento;
    }

    // Constructor vacío
    public Administrador() {
    }

    // Getters y Setters
    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
