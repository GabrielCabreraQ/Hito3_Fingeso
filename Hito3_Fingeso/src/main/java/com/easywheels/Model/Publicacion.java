package com.easywheels.Model;

import jakarta.persistence.*;

@Entity
public class Publicacion {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPublicacion;

    @OneToOne
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    private int precioNormal;
    private String localidad;
    private String codigoACRISS;
    private Boolean visibilidad;

    // Constructor
    public Publicacion(long idPublicacion, Vehiculo vehiculo, int precioNormal,
                       String localidad, String codigoACRISS, Boolean visibilidad) {
        this.idPublicacion = idPublicacion;
        this.vehiculo = vehiculo;
        this.precioNormal = precioNormal;
        this.localidad = localidad;
        this.codigoACRISS = codigoACRISS;
        this.visibilidad = visibilidad;
    }
    public Publicacion() {}

    // Getters y Setters
    public long getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(long idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public int getPrecioNormal() {
        return precioNormal;
    }

    public void setPrecioNormal(int precioNormal) {
        this.precioNormal = precioNormal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCodigoACRISS() {
        return codigoACRISS;
    }

    public void setCodigoACRISS(String codigoACRISS) {
        this.codigoACRISS = codigoACRISS;
    }

    public Boolean getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(Boolean visibilidad) {
        this.visibilidad = visibilidad;
    }

}
