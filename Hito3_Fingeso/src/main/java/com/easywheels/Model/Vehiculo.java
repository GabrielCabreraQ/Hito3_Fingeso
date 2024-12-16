package com.easywheels.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
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

    private Boolean disponible_uso;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Permite la serialización de la lista de informes
    private List<Informe> informes = new ArrayList<>();

    public Vehiculo() {
    }

    public Vehiculo(String marca, String modelo, int anio, String tipoTransmision,
                    String categoria, String tipoCuerpo, String combustibleAC, List<LocalDate> disponibilidad,
                    Boolean devuelto, Boolean disponible_uso) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.tipoTransmision = tipoTransmision;
        this.categoria = categoria;
        this.tipoCuerpo = tipoCuerpo;
        this.combustibleAC = combustibleAC;
        this.disponibilidad = disponibilidad;
        this.devuelto = devuelto;
        this.disponible_uso = disponible_uso;

    }

    //Getters y setters
    public List<Informe> getInformes() {return informes;}

    public void setInformes(List<Informe> informes) {this.informes = informes;}

    public Boolean getDisponible_uso() {return disponible_uso;}
    public void setDisponible_uso(Boolean disponible_uso) {this.disponible_uso = disponible_uso;}

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

    //Métodos para gestionar informes
    public void agregarInforme(Informe informe) {
        this.informes.add(informe);
        informe.setVehiculo(this);
        this.devuelto = true; // Marcar como devuelto cada vez que se genera un informe de devolución

    }

    public Informe obtenerUltimoInforme() {
        return this.informes.stream()
                .max(Comparator.comparingLong(Informe::getId))
                .orElse(null); // Retorna null si no hay informes
    }

}
