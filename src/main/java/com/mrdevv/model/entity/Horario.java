package com.mrdevv.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "fecha")
    Date fecha;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "hora_inicio")
    String horaInicio;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "hora_fin")
    String horaFin;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "estado")
    Boolean estado;

    @ManyToOne
    @JoinColumn(name = "id_odontologo")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Odontologo odontologo;

    @ManyToOne()
    @JoinColumn(name = "id_usuario")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Usuario usuario;

    public Horario(Integer idHorario, Odontologo odontologo, Date fecha, String horaInicio, String horaFin){
        this.idHorario = idHorario;
        this.odontologo = odontologo;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }
}
