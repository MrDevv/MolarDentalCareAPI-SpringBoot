package com.mrdevv.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CitaDto {
    Integer idCita;
    Integer idHorario;
    Integer idPaciente;
    Integer idUsuario;
    String estado;
}
