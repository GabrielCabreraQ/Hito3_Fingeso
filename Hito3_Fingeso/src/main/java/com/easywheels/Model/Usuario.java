package com.easywheels.Model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)   // Indicamos estrategia de tabla unica para las clases heredadas
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)   // nombrar a atributo tipo user
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo_usuario"
)
public abstract class Usuario {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUsuario;
    private String nombreUsuario;
    private String correoUsuario;
    private String contraseniaUsuario;
    private String telefonoUsuario;
    private LocalDate fechaNacimiento;
    //private String rol;         // De igual modo hay que heredar para crear tipo de user
    @Transient
    private List<Notificacion> notificaciones;                                           // Agregar posteriormente

    public Usuario() {
    }

    // Constructor sin el ID
    public Usuario(String nombreUsuario, String correoUsuario, String contraseniaUsuario, String telefonoUsuario, LocalDate fechaNacimiento, List<Notificacion> notificaciones) {
        this.nombreUsuario = nombreUsuario;
        this.correoUsuario = correoUsuario;
        this.contraseniaUsuario = contraseniaUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.fechaNacimiento = fechaNacimiento;
        this.notificaciones = notificaciones;
    }

    //constructor para el registro
    public Usuario(String nombreUsuario, String correoUsuario, String contraseniaUsuario, String telefonoUsuario, LocalDate fechaNacimiento) {
        this.nombreUsuario = nombreUsuario;
        this.correoUsuario = correoUsuario;
        this.contraseniaUsuario = contraseniaUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.fechaNacimiento = fechaNacimiento;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getContraseniaUsuario() {
        return contraseniaUsuario;
    }

    public void setContraseniaUsuario(String contraseniaUsuario) {
        this.contraseniaUsuario = contraseniaUsuario;
    }

    public String getTelefonoUsuario() {
        return telefonoUsuario;
    }

    public void setTelefonoUsuario(String telefonoUsuario) {
        this.telefonoUsuario = telefonoUsuario;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(List<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }
}
