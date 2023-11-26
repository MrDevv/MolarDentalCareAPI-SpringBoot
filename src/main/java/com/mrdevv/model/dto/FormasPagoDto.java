package com.mrdevv.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FormasPagoDto {
    Integer idFormaPago;
    String descripcion;
}
