package com.easywheels.Model;

import java.util.Date;

public class Boleta {                   // Falta relacionar con la base de datos

    // Atributos
    private Date fechaPago;
    private int montoTotal;
    private String estado;
    private String metodoDePago;

    // Constructor
    public Boleta(Date fechaPago, int montoTotal, String estado, String metodoDePago) {
        this.fechaPago = fechaPago;
        this.montoTotal = montoTotal;
        this.estado = estado;
        this.metodoDePago = metodoDePago;
    }
    public Boleta() {}

    //Getters y setters
    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(int montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMetodoDePago() {
        return metodoDePago;
    }

    public void setMetodoDePago(String metodoDePago) {
        this.metodoDePago = metodoDePago;
    }
}
