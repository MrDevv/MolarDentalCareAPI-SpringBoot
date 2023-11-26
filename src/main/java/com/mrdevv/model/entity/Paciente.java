package com.mrdevv.model.entity;

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
    @Column(name = "apellido")
    String apellido;
    @Column(name = "nombre")
    String nombre;
    @Column(name = "dni")
    String dni;
    @Column(name = "email")
    String email;
    @Column(name = "direccion")
    String direccion;
    @Column(name = "telefono")
    String telefono;
    @Column(name = "fecha_de_nacimiento")
    String fechaDeNacimiento;
}
