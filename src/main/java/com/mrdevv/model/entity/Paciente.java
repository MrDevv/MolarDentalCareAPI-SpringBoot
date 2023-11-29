package com.mrdevv.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paciente")
    Integer idPaciente;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "apellido")
    String apellido;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "nombre")
    String nombre;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "dni")
    String dni;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "email")
    String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "direccion")
    String direccion;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "telefono")
    String telefono;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "fecha_de_nacimiento")
    String fechaDeNacimiento;

    public Paciente(Integer idPaciente, String apellido, String nombre){
        this.idPaciente = idPaciente;
        this.apellido = apellido;
        this.nombre = nombre;
    }
}
