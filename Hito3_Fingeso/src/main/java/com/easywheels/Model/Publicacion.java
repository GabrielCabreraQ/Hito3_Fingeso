package com.easywheels.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity

public class Publicacion {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPublicacion;

    @OneToOne
    @JoinColumn(name = "idVehiculo")
    private Vehiculo vehiculo;

    private int precioNormal;
    private String codigoACRISS;
    private Boolean visibilidad;

    @ManyToOne
    @JoinColumn(name = "idCatalogo")
    @JsonBackReference
    @JsonIgnore
    private Catalogo catalogo;

    @JsonGetter("IdCatalogo")       //Nombramos atributo idCatalogo para mandar en el json
    public Long getCatalogoId() {
        return catalogo != null ? catalogo.getIdCatalogo() : null;
    }

    // Constructores
    public Publicacion() {
    }

    public Publicacion( Vehiculo vehiculo, int precioNormal, String codigoACRISS, Boolean visibilidad, Catalogo catalogo) {
        this.vehiculo = vehiculo;
        this.precioNormal = precioNormal;
        this.codigoACRISS = codigoACRISS;
        this.visibilidad = visibilidad;
        this.catalogo = catalogo;
    }

    // Metodos
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
