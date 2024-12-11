package com.easywheels.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Arriendo {             //Falta relacioanr con la baseee

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

    // Constructor
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
    public Arriendo() {}

    // Getters y setters
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Arrendatario getArrendatario() {
        return arrendatario;
    }

    public void setArrendatario(Arrendatario arrendatario) {
        this.arrendatario = arrendatario;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Boolean getConformidad() {
        return conformidad;
    }

    public void setConformidad(Boolean conformidad) {
        this.conformidad = conformidad;
    }

    public Boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(Boolean cancelado) {
        this.cancelado = cancelado;
    }

    public Boleta getBoleta() {
        return boleta;
    }

    public void setBoleta(Boleta boleta) {
        this.boleta = boleta;
    }
}