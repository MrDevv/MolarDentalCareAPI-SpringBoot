package com.mrdevv.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PacienteDto {
    Integer idPaciente;
    String apellido;
    String nombre;
    String dni;
    String email;
    String direccion;
    String telefono;
    String fechaDeNacimiento;
}
