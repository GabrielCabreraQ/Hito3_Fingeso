package com.easywheels.Model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("arrendatario")
public class Arrendatario extends Usuario{

    // Atributos
    @ElementCollection
    private List<String> tipoLicencia;
    @ElementCollection
    private List<String> documentos;
    // Posible atributo para almacenar los arriendos de cada arrendatario
    //@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    //private List<Arriendo> arriendos;


    public Arrendatario() {
    }

    public Arrendatario(String nombreUsuario, String correoUsuario, String contraseniaUsuario, String telefonoUsuario, LocalDate fechaNacimiento, List<String> tipoLicencia, List<String> documentos) {
        super(nombreUsuario, correoUsuario, contraseniaUsuario, telefonoUsuario, fechaNacimiento);
        this.tipoLicencia = tipoLicencia;
        this.documentos = documentos;
    }

    public Arrendatario(List<String> tipoLicencia, List<String> documentos) {
        this.tipoLicencia = tipoLicencia;
        this.documentos = documentos;
    }

    public List<String> getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(List<String> tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    public List<String> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<String> documentos) {
        this.documentos = documentos;
    }
}