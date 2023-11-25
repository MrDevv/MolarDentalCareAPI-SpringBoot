package com.mrdevv.model.entity;

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
    @Column(name = "id_rol")
    Integer idRol;
    @Column(name = "apellido")
    String apellido;
    @Column(name = "nombre")
    String nombre;
    @Column(name = "dni")
    String dni;
    @Column(name = "email")
    String email;
    @Column(name = "telefono")
    String telefono;
    @Column(name = "usuario")
    String usuario;
    @Column(name = "password")
    String password;
    @Column(name = "estado")
    boolean estado;
}
