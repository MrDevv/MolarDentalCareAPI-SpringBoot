package com.mrdevv.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "citas")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    Integer idCita;
    @Column(name = "id_horario")
    Integer idHorario;
    @Column(name = "id_paciente")
    Integer idPaciente;
    @Column(name = "id_usuario")
    Integer idUsuario;
    @Column(name = "estado")
    String estado;
}
