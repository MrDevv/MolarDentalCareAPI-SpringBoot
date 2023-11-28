package com.mrdevv.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "horarios")
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horario")
    Integer idHorario;
    @Column(name = "fecha")
    Date fecha;
    @Column(name = "hora_inicio")
    String horaInicio;
    @Column(name = "hora_fin")
    String horaFin;
    @Column(name = "estado")
    boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_odontologo")
    Odontologo odontologo;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    Usuario usuario;
}
