package com.mrdevv.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "rol")
public class Rol {
    @Id
    @Column(name = "id_rol")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idRol;
    @Column(name = "descripcion")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String descripcion;
}
