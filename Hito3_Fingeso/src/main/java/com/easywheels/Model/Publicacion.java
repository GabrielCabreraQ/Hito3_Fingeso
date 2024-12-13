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
    private String codigoACRISS;
    private Boolean visibilidad;

    @ManyToOne
    @JoinColumn(name = "catalogo_id", nullable = false)
    private Catalogo catalogo;

    public Publicacion() {
    }

    public Publicacion(long idPublicacion, Vehiculo vehiculo, int precioNormal, String codigoACRISS, Boolean visibilidad, Catalogo catalogo) {
        this.idPublicacion = idPublicacion;
        this.vehiculo = vehiculo;
        this.precioNormal = precioNormal;
        this.codigoACRISS = codigoACRISS;
        this.visibilidad = visibilidad;
        this.catalogo = catalogo;
    }

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

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }
}
