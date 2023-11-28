package com.mrdevv.model.dto;

import com.mrdevv.model.entity.Odontologo;
import com.mrdevv.model.entity.Usuario;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class HorarioDto {
    Integer idHorario;
    Date fecha;
    String horaInicio;
    String horaFin;
    boolean estado;

    Odontologo odontologo;
    Usuario usuario;
}
