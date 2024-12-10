package com.easywheels.Model;

public class Notificacion {

    // Atributos
    private String mensaje;
    private String titulo;

    // Constructor
    public Notificacion(String mensaje, String titulo) {
        this.mensaje = mensaje;
        this.titulo = titulo;
    }

    // Getters y setters
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
