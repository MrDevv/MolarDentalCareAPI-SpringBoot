package com.mrdevv.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    Integer idUsuario;

    @NotBlank(message = "El apellido no puede estar vacio")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "apellido")
    String apellido;

    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "nombre")
    String nombre;

    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "dni")
    String dni;

    @NotBlank
    @Email
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "email")
    String email;

    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "telefono")
    String telefono;

    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "usuario")
    String usuario;

    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "password")
    String password;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "estado")
    Boolean estado;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ManyToOne()
    @JoinColumn(name = "id_rol")
    Rol rol;

    @NotBlank
    public Usuario(Integer idUsuario){
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, String apellido, String nombre, Boolean estado, Rol rol) {
        this.idUsuario = idUsuario;
        this.apellido = apellido;
        this.nombre = nombre;
        this.estado = estado;
        this.rol = rol;
    }
}
