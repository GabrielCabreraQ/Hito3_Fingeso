package com.easywheels.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("arrendatario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Arrendatario extends Usuario{

    // Atributos
    @ElementCollection
    private List<String> tipoLicencia;
    @ElementCollection
    private List<String> documentos;
    // Posible atributo para almacenar los arriendos de cada arrendatario
    //@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    //private List<Arriendo> arriendos;

}
