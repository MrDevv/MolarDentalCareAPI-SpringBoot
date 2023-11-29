package com.mrdevv.model.dto;

import com.mrdevv.model.entity.Horario;
import com.mrdevv.model.entity.Paciente;
import com.mrdevv.model.entity.Usuario;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CitaDto {
    Integer idCita;
    String estado;
    Horario horario;
    Paciente paciente;
    Usuario usuario;
}
