package com.istateca.app.istateca.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "persona")
public class Persona implements Serializable,Actualizable<Persona> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "per_id")
    private Integer id;

    @Column(name = "per_fenix_id", unique = true)
    private Integer fenixId;

    @NotEmpty(message = "Campo de cedula obligatorio.")
    @Column(name = "per_cedula")
    private String cedula;

    @Email(message = "No corresponde a un Email.")
    @Column(name = "per_correo")
    private String correo;

    @NotEmpty(message = "Campo nombre obligatorio.")
    @Column(name = "per_nombres")
    private String nombres;

    @NotEmpty(message = "Campo apellido obligatorio.")
    @Column(name = "per_apellidos")
    private String apellidos;

    @NotEmpty(message = "Direccion Obligatoria.")
    @Column(name = "per_direccion")
    private String direccion;

    //@NotEmpty(message = "Campo apellido obligatorio.")
    @Column(name = "per_password")
    private String password;

    @Column(name = "per_tipo")
    private Integer tipo;

    @Pattern(regexp = "\\d+", message = "El teléfono debe contar solo con números")
    @Column(name = "per_celular")
    private String celular;

    @Column(name = "per_calificacion")
    @Min(0) @Max(5)
    private Integer calificacion;

    @Column(name = "per_activo")
    private Boolean activo;

    // Bidirectional Relationships

    @JsonIgnore
    @OneToOne(mappedBy = "persona", fetch = FetchType.EAGER)
    private Authority authorities;

    @JsonIgnore
    @OneToMany(mappedBy = "persona", fetch = FetchType.LAZY)
    private List<BibliotecarioCargo> bibliotecaCargos;

    @JsonIgnore
    @OneToMany(mappedBy = "idSolicitante", fetch = FetchType.LAZY)
    private List<Prestamo> solicitudesPrestamo;

    @JsonIgnore
    @OneToMany(mappedBy = "idEntrega", fetch = FetchType.LAZY)
    private List<Prestamo> prestamosEntregados;

    @JsonIgnore
    @OneToMany(mappedBy = "idRecibido", fetch = FetchType.LAZY)
    private List<Prestamo> prestamosRecibidos;

    @JsonIgnore
    @OneToMany(mappedBy = "persona", fetch = FetchType.LAZY)
    private List<Libro> librosIngresados;

    @Override
    public void actualizarDatos(Persona entity) {
        if (entity.getCedula() != null) {
            this.setCedula(entity.getCedula());
        }
        if (entity.getCorreo() != null) {
            this.setCorreo(entity.getCorreo());
        }
        if (entity.getNombres() != null) {
            this.setNombres(entity.getNombres());
        }
        if (entity.getApellidos() != null) {
            this.setApellidos(entity.getApellidos());
        }
        if (entity.getTipo() != null) {
            this.setTipo(entity.getTipo());
        }
        if (entity.getCelular() != null) {
            this.setCelular(entity.getCelular());
        }
        if (entity.getCalificacion() != null) {
            this.setCalificacion(entity.getCalificacion());
        }
        if (entity.getActivo() != null) {
            this.setActivo(entity.getActivo());
        }
        if (entity.getAuthorities() != null) {
            this.setAuthorities(entity.getAuthorities());
        }
        if (entity.getPassword() != null) {
            this.setPassword(entity.getPassword());
        }
    }
}
