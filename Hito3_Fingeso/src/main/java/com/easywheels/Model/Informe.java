package com.easywheels.Model;

public class Informe {

    // Atributos
    private Vehiculo vehiculoMasArrendado;
    private String tipovehiculoMasArrendado;
    private String localidadVehiculoMasArrendado;

    // Constructor
    public Informe(Vehiculo vehiculoMasArrendado, String tipovehiculoMasArrendado, String localidadVehiculoMasArrendado) {
        this.vehiculoMasArrendado = vehiculoMasArrendado;
        this.tipovehiculoMasArrendado = tipovehiculoMasArrendado;
        this.localidadVehiculoMasArrendado = localidadVehiculoMasArrendado;
    }

    // Metodos
    public Vehiculo getVehiculoMasArrendado() {
        return vehiculoMasArrendado;
    }

    public void setVehiculoMasArrendado(Vehiculo vehiculoMasArrendado) {
        this.vehiculoMasArrendado = vehiculoMasArrendado;
    }

    public String getTipovehiculoMasArrendado() {
        return tipovehiculoMasArrendado;
    }

    public void setTipovehiculoMasArrendado(String tipovehiculoMasArrendado) {
        this.tipovehiculoMasArrendado = tipovehiculoMasArrendado;
    }

    public String getLocalidadVehiculoMasArrendado() {
        return localidadVehiculoMasArrendado;
    }

    public void setLocalidadVehiculoMasArrendado(String localidadVehiculoMasArrendado) {
        this.localidadVehiculoMasArrendado = localidadVehiculoMasArrendado;
    }
    
}
