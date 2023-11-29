package com.mrdevv.controller;

import com.mrdevv.model.dto.CitaDto;
import com.mrdevv.model.entity.*;
import com.mrdevv.model.payload.MensajeResponse;
import com.mrdevv.service.ICitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

            List<CitaDto> citasDto = citas.stream().map(cita -> CitaDto.builder()
                    .idCita(cita.getIdCita())
                    .estado(cita.getEstado())
                    .horario(new Horario(
                            cita.getHorario().getIdHorario(),
                            new Odontologo(
                                    cita.getHorario().getOdontologo().getIdOdontologo(),
                                    cita.getHorario().getOdontologo().getNombre(),
                                    cita.getHorario().getOdontologo().getApellido()
                            ),
                            cita.getHorario().getFecha(),
                            cita.getHorario().getHoraInicio(),
                            cita.getHorario().getHoraFin()
                    ))
                    .paciente(new Paciente(
                            cita.getPaciente().getIdPaciente(),
                            cita.getPaciente().getApellido(),
                            cita.getPaciente().getNombre()
                    ))
                    .usuario(new Usuario(cita.getUsuario().getIdUsuario()))
                    .build()
            ).toList();
            return ResponseEntity.ok(
                    MensajeResponse.builder()
                            .mensaje(citasDto.isEmpty() ? "No hay registros en la base de datos" : "Ok")
                            .object(citasDto)
                            .build()
            );
        } catch (DataAccessException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(MensajeResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build());
        }
    }

    @PostMapping("cita")
    public ResponseEntity<?> guardarCita(@RequestBody CitaDto citaDto) {
        try {
            Cita citaCreated = citaService.guardar(citaDto);
            return ResponseEntity.created(URI.create("/api/v1/cita")).body(
                    MensajeResponse.builder()
                            .mensaje("Ok")
                            .object(CitaDto.builder()
                                    .idCita(citaCreated.getIdCita())
                                    .estado(citaCreated.getEstado())
                                    .horario(citaCreated.getHorario())
                                    .paciente(citaCreated.getPaciente())
                                    .usuario(citaCreated.getUsuario())
                                    .build()
                            )
                            .build()
            );
        } catch (DataAccessException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(MensajeResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build());
        }
    }

    @PutMapping("cita/{id}")
    public ResponseEntity<?> actualizarCita(@RequestBody CitaDto citaDto, @PathVariable Integer id) {
        try {
            if (citaService.existePorId(id)) {
                citaDto.setIdCita(id);
                Cita citaUpdated = citaService.guardar(citaDto);
                return ResponseEntity.ok(
                        MensajeResponse.builder()
                                .mensaje("Se actualizó correctamente la cita")
                                .object(CitaDto.builder()
                                        .idCita(citaUpdated.getIdCita())
                                        .estado(citaUpdated.getEstado())
                                        .horario(new Horario(
                                                citaUpdated.getHorario().getIdHorario(),
                                                new Odontologo(
                                                        citaUpdated.getHorario().getOdontologo().getIdOdontologo(),
                                                        citaUpdated.getHorario().getOdontologo().getNombre(),
                                                        citaUpdated.getHorario().getOdontologo().getApellido()
                                                ),
                                                citaUpdated.getHorario().getFecha(),
                                                citaUpdated.getHorario().getHoraInicio(),
                                                citaUpdated.getHorario().getHoraFin()
                                        ))
                                        .paciente(new Paciente(
                                                citaUpdated.getPaciente().getIdPaciente(),
                                                citaUpdated.getPaciente().getApellido(),
                                                citaUpdated.getPaciente().getNombre()
                                        ))
                                        .usuario(new Usuario(citaUpdated.getUsuario().getIdUsuario()))
                                        .build()
                                )
                                .build()
                );
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    MensajeResponse.builder()
                            .mensaje("No existe la cita con el ID ingresado")
                            .object(null)
                            .build()
            );
        } catch (DataAccessException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(MensajeResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build());
        }
    }

    @DeleteMapping("cita/{id}")
    public ResponseEntity<?> eliminarCita(@PathVariable Integer id) {
        try {
            if (citaService.existePorId(id)) {
                citaService.eliminarPorId(id);
                return ResponseEntity.ok(
                        MensajeResponse.builder()
                                .mensaje("Se eliminó correctamente la cita")
                                .object(null)
                                .build()
                );
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    MensajeResponse.builder()
                            .mensaje("No existe la cita con el ID ingresado")
                            .object(null)
                            .build()
            );
        } catch (DataAccessException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(MensajeResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build());
        }
    }
}
