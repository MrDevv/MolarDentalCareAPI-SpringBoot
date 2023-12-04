package com.mrdevv.model.dto;

import com.mrdevv.model.entity.Cita;
import com.mrdevv.model.entity.FormasPago;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PagoDto {
    Integer idPagos;
    Date fechaPago;
    Double montoTotal;
    Boolean estado;
    Cita cita;
    FormasPago formasPago;
}
