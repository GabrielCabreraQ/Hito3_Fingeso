package com.easywheels.Model;

public class SolicitudCancelacion {

    // Atributos
    private Arrendatario arrendatario;
    private Arriendo arriendo;
    private String motivo;
    private Boolean aceptacion;

    // Contructor
    public SolicitudCancelacion(Arrendatario arrendatario, Arriendo arriendo,
                                String motivo, Boolean aceptacion) {
        this.arrendatario = arrendatario;
        this.arriendo = arriendo;
        this.motivo = motivo;
        this.aceptacion = aceptacion;
    }

    // Getters y setters
    public Arrendatario getArrendatario() {
        return arrendatario;
    }

    public void setArrendatario(Arrendatario arrendatario) {
        this.arrendatario = arrendatario;
    }

    public Arriendo getArriendo() {
        return arriendo;
    }

    public void setArriendo(Arriendo arriendo) {
        this.arriendo = arriendo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Boolean getAceptacion() {
        return aceptacion;
    }

    public void setAceptacion(Boolean aceptacion) {
        this.aceptacion = aceptacion;
    }
}
