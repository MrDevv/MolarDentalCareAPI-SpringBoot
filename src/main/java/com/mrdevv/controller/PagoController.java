package com.mrdevv.controller;

import com.mrdevv.model.dto.PagoDto;
import com.mrdevv.model.entity.Cita;
import com.mrdevv.model.entity.FormasPago;
import com.mrdevv.model.entity.Horario;
import com.mrdevv.model.entity.Pago;
import com.mrdevv.model.payload.MensajeResponse;
import com.mrdevv.service.IPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
                            .estado(pago.getEstado())
                            .cita(new Cita(
                                            pago.getCita().getIdCita(),
                                            new Horario(
                                                    pago.getCita().getHorario().getIdHorario(),
                                                    pago.getCita().getHorario().getFecha())
                                    )
                            )
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
    }

    @PostMapping("pago")
    public ResponseEntity<?> guardarPago(@RequestBody PagoDto pagoDto) {
        try {
            Pago pagoCreated = pagoService.guardar(pagoDto);

            return ResponseEntity.created(URI.create("api/v1/pago"))
                    .body(
                            MensajeResponse.builder()
                                    .mensaje("El pago se registró correctamente")
                                    .object(PagoDto.builder()
                                            .idPagos(pagoCreated.getIdPagos())
                                            .fechaPago(pagoCreated.getFechaPago())
                                            .montoTotal(pagoCreated.getMontoTotal())
                                            .estado(pagoCreated.getEstado())
                                            .cita(pagoCreated.getCita())
                                            .formasPago(pagoCreated.getFormasPago())
                                            .build()
                                    )
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
    }

    @PutMapping("pago/{id}")
    public ResponseEntity<?> actualizarPago(@RequestBody PagoDto pagoDto, @PathVariable Integer id) {
        try {
            if (pagoService.existePorId(id)) {
                pagoDto.setIdPagos(id);
                Pago pagoUpdated = pagoService.guardar(pagoDto);

                return ResponseEntity.ok(
                        MensajeResponse.builder()
                                .mensaje("El pago se actualizó correctamente")
                                .object(PagoDto.builder()
                                        .idPagos(pagoUpdated.getIdPagos())
                                        .fechaPago(pagoUpdated.getFechaPago())
                                        .montoTotal(pagoUpdated.getMontoTotal())
                                        .estado(pagoUpdated.getEstado())
                                        .cita(new Cita(
                                                pagoUpdated.getCita().getIdCita())
                                        )
                                        .formasPago(pagoUpdated.getFormasPago())
                                        .build()
                                )
                                .build()
                );
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    MensajeResponse.builder()
                            .mensaje("No existe el pago con el id ingresado")
                            .object(null)
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
    }

    @DeleteMapping("pago/{id}")
    public ResponseEntity<?> eliminarPago(@PathVariable Integer id) {
        try {
            if (pagoService.existePorId(id)) {
                pagoService.eliminarPorId(id);
                return ResponseEntity.ok(
                        MensajeResponse.builder()
                                .mensaje("Se eliminó correctamente el pago")
                                .object(null)
                                .build()
                );
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    MensajeResponse.builder()
                            .mensaje("No existe el pago con el ID ingresado")
                            .object(null)
                            .build()
            );
        } catch (DataAccessException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(MensajeResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build()
                    );
        }
    }
}
