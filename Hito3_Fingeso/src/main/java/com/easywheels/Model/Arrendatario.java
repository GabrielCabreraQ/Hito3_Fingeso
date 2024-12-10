package com.easywheels.Model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;

import java.util.Date;
import java.util.List;

@Entity
public class Arrendatario extends Usuario{

    // Atributos
    @ElementCollection
    private List<String> tipoLicencia;
    @ElementCollection
    private List<String> documentos;

    // Constructor
    public Arrendatario(long idUsuario, String nombreUsuario, String correoUsuario,
                        String contraseniaUsuario, String telefonoUsuario, Date fechaNacimiento,
                        String rol, List<String> tipoLicencia, List<String> documentos) {
        super(idUsuario, nombreUsuario, correoUsuario, contraseniaUsuario, telefonoUsuario,
                fechaNacimiento, rol);
        this.tipoLicencia = tipoLicencia;
        this.documentos = documentos;
    }

    public Arrendatario(List<String> tipoLicencia, List<String> documentos) {
        this.tipoLicencia = tipoLicencia;
        this.documentos = documentos;
    }

    public Arrendatario() {}

    // Getters y Setters
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
