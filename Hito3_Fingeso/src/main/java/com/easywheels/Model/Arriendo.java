package com.easywheels.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Arriendo {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)      // se relaciona con el atri clienteID y no puede ser null
    private Arrendatario arrendatario;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id", nullable = false)
    private Vehiculo vehiculo;

    private int precio;
    private Date fechaInicio;
    private Date fechaFin;
    private Boolean conformidad;
    private Boolean cancelado;
    @Transient
    private Boleta boleta = new Boleta();

    // Constructor sin Boleta
    public Arriendo(Vehiculo vehiculo, Arrendatario arrendatario,
                    int precio, Date fechaInicio, Date fechaFin,
                    Boolean conformidad, Boolean cancelado) {
        this.vehiculo = vehiculo;
        this.arrendatario = arrendatario;
        this.precio = precio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.conformidad = conformidad;
        this.cancelado = cancelado;
    }

}
