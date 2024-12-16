package com.easywheels.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Catalogo {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCatalogo;

    @OneToMany(mappedBy = "catalogo", cascade = CascadeType.ALL)   // si se eliimna un catalogo, tambien sus publicaciones
    @JsonManagedReference       // Mandamos en el json, toda la info de las publicaciones del catalogo
    private List<Publicacion> disponibles;
    private String departamento;

    public Catalogo(long idCatalogo, List<Publicacion> disponibles, String departamento) {
        this.idCatalogo = idCatalogo;
        this.disponibles = disponibles;
        this.departamento = departamento;
    }

    public Catalogo() {}

    public long getIdCatalogo() {
        return idCatalogo;
    }

    public void setIdCatalogo(long idCatalogo) {
        this.idCatalogo = idCatalogo;
    }

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
