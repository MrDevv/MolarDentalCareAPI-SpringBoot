package com.mrdevv.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class HorarioDto {
    Integer idHorario;
    Integer idOdontologo;
    Integer idUsuario;
    Date fecha;
    String horaInicio;
    String horaFin;
    boolean estado;
}
