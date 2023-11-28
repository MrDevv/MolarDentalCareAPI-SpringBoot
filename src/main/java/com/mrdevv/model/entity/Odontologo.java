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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "odontologos")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_odontologo")
    Integer idOdontologo;

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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "estado")
    Boolean estado;

    public Odontologo(Integer idOdontologo){
        this.idOdontologo = idOdontologo;
    }

    public Odontologo(Integer idOdontologo, String nombre, String apellido){
        this.idOdontologo = idOdontologo;
        this.nombre = nombre;
        this.apellido = apellido;
    }
}
