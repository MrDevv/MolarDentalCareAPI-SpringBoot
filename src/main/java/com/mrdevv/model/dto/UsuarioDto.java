package com.mrdevv.model.dto;

import com.mrdevv.model.entity.Rol;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDto {
    Integer idUsuario;
    String apellido;
    String nombre;
    String dni;
    String email;
    String telefono;
    String usuario;
    String password;
    boolean estado;
    Rol rol;
}
