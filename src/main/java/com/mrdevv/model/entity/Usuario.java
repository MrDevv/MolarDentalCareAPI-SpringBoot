package com.mrdevv.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "apellido")
    String apellido;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "nombre")
    String nombre;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "dni")
    String dni;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "email")
    String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "telefono")
    String telefono;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "usuario")
    String usuario;

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
