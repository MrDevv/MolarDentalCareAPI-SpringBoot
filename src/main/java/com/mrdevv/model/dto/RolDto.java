package com.mrdevv.model.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolDto {
    Integer idRol;
    String descripcion;
}
