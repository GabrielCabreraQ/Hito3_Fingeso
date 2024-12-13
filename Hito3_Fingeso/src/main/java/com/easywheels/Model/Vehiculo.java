package com.easywheels.Model;

import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.List;

@Entity     // Indicamos a Spring que es un objeto entidad para la tabla vehiculo
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehiculo;

    private String marca;
    private String modelo;
    private int anio;
    private String tipoTransmision;
    private String categoria;
    private String tipoCuerpo;
    private String combustibleAC;

    @ElementCollection
    private List<LocalDate> disponibilidad; // List<LocalDate>

    private Boolean devuelto;

    public Vehiculo() {
    }

    public Vehiculo(String marca, String modelo, int anio, String tipoTransmision,
                    String categoria, String tipoCuerpo, String combustibleAC, List<LocalDate> disponibilidad,
                    Boolean devuelto) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.tipoTransmision = tipoTransmision;
        this.categoria = categoria;
        this.tipoCuerpo = tipoCuerpo;
        this.combustibleAC = combustibleAC;
        this.disponibilidad = disponibilidad;
        this.devuelto = devuelto;
    }

    public Long getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Long idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getTipoTransmision() {
        return tipoTransmision;
    }

    public void setTipoTransmision(String tipoTransmision) {
        this.tipoTransmision = tipoTransmision;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTipoCuerpo() {
        return tipoCuerpo;
    }

    public void setTipoCuerpo(String tipoCuerpo) {
        this.tipoCuerpo = tipoCuerpo;
    }

    public String getCombustibleAC() {
        return combustibleAC;
    }

    public void setCombustibleAC(String combustibleAC) {
        this.combustibleAC = combustibleAC;
    }

    public List<LocalDate> getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(List<LocalDate> disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Boolean getDevuelto() {
        return devuelto;
    }

    public void setDevuelto(Boolean devuelto) {
        this.devuelto = devuelto;
    }
}
