package com.easywheels.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)   // Indicamos estrategia de tabla unica para las clases heredadas
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)   // nombrar a atributo tipo user
@Data                       // Lombok agrega getters y setters
@AllArgsConstructor         // Lombok agrega constructor con todos los argumentos
@NoArgsConstructor          // Lombok agrega constructor vacio
public abstract class Usuario {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUsuario;
    private String nombreUsuario;
    private String correoUsuario;
    private String contraseniaUsuario;
    private String telefonoUsuario;
    private Date fechaNacimiento;
    //private String rol;         // De igual modo hay que heredar para crear tipo de user
    @Transient
    private List<Notificacion> notificaciones;                                           // Agregar posteriormente

    // Constructor sin lista de notificaciones
    public Usuario(long idUsuario, String nombreUsuario, String correoUsuario, String contraseniaUsuario,
                   String telefonoUsuario, Date fechaNacimiento) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.correoUsuario = correoUsuario;
        this.contraseniaUsuario = contraseniaUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.fechaNacimiento = fechaNacimiento;
    }


}
