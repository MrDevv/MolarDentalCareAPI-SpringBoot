package com.mrdevv.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDto {
    Integer idUsuario;
    Integer idRol;
    String apellido;
    String nombre;
    String dni;
    String email;
    String telefono;
    String usuario;
    String password;
    boolean estado;
}
