package com.mrdevv.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pagos")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagos")
    Integer idPagos;

    @Column(name = "fecha_pago")
    Date fechaPago;

    @Column(name = "monto_total")
    Double montoTotal;

    @ManyToOne
    @JoinColumn(name = "id_cita")
    Cita cita;

    @ManyToOne
    @JoinColumn(name = "id_forma_pago")
    FormasPago formasPago;
}
