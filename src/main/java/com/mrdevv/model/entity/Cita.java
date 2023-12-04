package com.mrdevv.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "estado")
    String estado;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JoinColumn(name = "id_horario")
    @OneToOne
    Horario horario;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ManyToOne
    @JoinColumn(name = "id_paciente")
    Paciente paciente;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    Usuario usuario;

    public Cita(Integer idCita){
        this.idCita = idCita;
    }

    public Cita(Integer idCita, Horario horario){
        this.idCita = idCita;
        this.horario = horario;
    }
}
