package com.mrdevv.model.payload;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MensajeResponse {
    String mensaje;
    Object object;
}
