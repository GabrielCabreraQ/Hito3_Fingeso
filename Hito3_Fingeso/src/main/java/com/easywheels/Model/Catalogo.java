package com.easywheels.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Catalogo {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "localidad", cascade = CascadeType.ALL)
    private List<Publicacion> disponibles;
    private String departamento;

    // Constructor
    public Catalogo(List<Publicacion> disponibles, String departamento) {
        this.disponibles = disponibles;
        this.departamento = departamento;
    }
    public Catalogo() {}

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
