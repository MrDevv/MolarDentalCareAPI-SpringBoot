package com.mrdevv.controller;

import com.mrdevv.model.dto.PagoDto;
import com.mrdevv.model.entity.Pago;
import com.mrdevv.model.payload.MensajeResponse;
import com.mrdevv.service.IPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PagoController {
    @Autowired
    IPagoService pagoService;

    @GetMapping("pagos")
    public ResponseEntity<?> listarPagos() {
        try {
            List<Pago> pagos = pagoService.listarTodos();

            List<PagoDto> pagosDto = pagos.stream().map(
                    pago -> PagoDto.builder()
                            .idPagos(pago.getIdPagos())
                            .fechaPago(pago.getFechaPago())
                            .montoTotal(pago.getMontoTotal())
                            .cita(pago.getCita())
                            .formasPago(pago.getFormasPago())
                            .build()
            ).toList();

            return ResponseEntity.ok(
                    MensajeResponse.builder()
                            .mensaje(pagosDto.isEmpty() ? "No hay registros en la base de datos" : "Ok")
                            .object(pagosDto)
                            .build()
            );

        } catch (DataAccessException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    MensajeResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build()
            );
        }
//
    }
}
