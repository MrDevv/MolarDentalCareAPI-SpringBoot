package com.mrdevv.controller;

import com.mrdevv.model.dto.CitaDto;
import com.mrdevv.model.entity.Cita;
import com.mrdevv.model.payload.MensajeResponse;
import com.mrdevv.service.ICitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CitaController {

    @Autowired
    ICitaService citaService;

    @GetMapping("citas")
    public ResponseEntity<?> listarCitas() {
        try {
            List<Cita> citas = citaService.listarTodos();

            if (citas.isEmpty()) {
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("No hay registros en la base de datos")
                                .object(citas)
                                .build()
                        , HttpStatus.OK
                );
            }
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Ok")
                            .object(citas)
                            .build()
                    , HttpStatus.OK
            );
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping("cita")
    public ResponseEntity<?> guardarCita(@RequestBody CitaDto citaDto) {
        try {
            Cita citaCreated = citaService.guardar(citaDto);
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Ok")
                            .object(CitaDto.builder()
                                    .idCita(citaCreated.getIdCita())
                                    .idHorario(citaCreated.getIdHorario())
                                    .idPaciente(citaCreated.getIdPaciente())
                                    .idUsuario(citaCreated.getIdUsuario())
                                    .estado(citaCreated.getEstado())
                                    .build()
                            )
                            .build()
                    , HttpStatus.CREATED
            );
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PutMapping("cita/{id}")
    public ResponseEntity<?> actualizarCita(@RequestBody CitaDto citaDto, @PathVariable Integer id) {
        try {
            if (citaService.existePorId(id)) {
                citaDto.setIdCita(id);
                Cita citaUpdated = citaService.guardar(citaDto);
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("Se actualizó correctamente la cita")
                                .object(CitaDto.builder()
                                        .idCita(citaUpdated.getIdCita())
                                        .idHorario(citaUpdated.getIdHorario())
                                        .idPaciente(citaUpdated.getIdPaciente())
                                        .idUsuario(citaUpdated.getIdUsuario())
                                        .estado(citaUpdated.getEstado())
                                        .build()
                                )
                                .build()
                        , HttpStatus.OK
                );
            }
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No existe la cita con el ID ingresado")
                            .object(null)
                            .build()
                    , HttpStatus.OK
            );
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @DeleteMapping("cita/{id}")
    public ResponseEntity<?> eliminarCita(@PathVariable Integer id) {
        try {
            if (citaService.existePorId(id)) {
                citaService.eliminarPorId(id);
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("Se eliminó correctamente la cita")
                                .object(null)
                                .build()
                        , HttpStatus.OK
                );
            }
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No existe la cita con el ID ingresado")
                            .object(null)
                            .build()
                    , HttpStatus.OK
            );
        } catch (DataAccessException ex) {
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
