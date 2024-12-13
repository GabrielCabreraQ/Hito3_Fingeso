package com.easywheels.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity

public class Catalogo {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "catalogo", cascade = CascadeType.ALL)   // si se eliimna un catalogo, tambien sus publicaciones
    private List<Publicacion> disponibles;
    private String departamento;

}
