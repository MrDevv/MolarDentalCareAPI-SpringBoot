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

    @Column(name = "estado")
    String estado;

    @JoinColumn(name = "id_horario")
    @OneToOne
    Horario horario;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    Usuario usuario;
}
