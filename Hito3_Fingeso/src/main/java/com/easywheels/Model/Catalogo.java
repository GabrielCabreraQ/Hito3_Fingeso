package com.easywheels.Model;

import java.util.List;

public class Catalogo {                                 // Falta relacionar con la base de datos

    // Atributos
    private List<Publicacion> disponibles;
    private String departamento;

    // Constructor
    public Catalogo(List<Publicacion> disponibles, String departamento) {
        this.disponibles = disponibles;
        this.departamento = departamento;
    }

    // Getters y setters
    public List<Publicacion> getDisponibles() {
        return disponibles;
    }

    public void setDisponibles(List<Publicacion> disponibles) {
        this.disponibles = disponibles;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
