package com.mrdevv.controller;

import com.mrdevv.model.dto.HorarioDto;
import com.mrdevv.model.entity.Horario;
import com.mrdevv.model.entity.Odontologo;
import com.mrdevv.model.entity.Usuario;
import com.mrdevv.model.payload.MensajeResponse;
import com.mrdevv.service.IHorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class HorarioController {
    @Autowired
    IHorarioService horarioService;

    @GetMapping("horarios")
    public ResponseEntity<?> listarHorarios() {
        try {
            List<Horario> horarios = horarioService.listarTodos();

            List<HorarioDto> horariosDto = horarios.stream().map(
                    horario -> HorarioDto.builder()
                            .idHorario(horario.getIdHorario())
                            .fecha(horario.getFecha())
                            .horaInicio(horario.getHoraInicio())
                            .horaFin(horario.getHoraFin())
                            .odontologo(new Odontologo(
                                    horario.getOdontologo().getIdOdontologo(),
                                    horario.getOdontologo().getNombre(),
                                    horario.getOdontologo().getApellido()))
                            .usuario(new Usuario(
                                    horario.getUsuario().getIdUsuario(),
                                    horario.getUsuario().getApellido(),
                                    horario.getUsuario().getNombre(),
                                    horario.getUsuario().getEstado(),
                                    horario.getUsuario().getRol()))
                            .build()
            ).toList();
            return ResponseEntity.ok(
                    MensajeResponse.builder()
                            .mensaje(horariosDto.isEmpty() ? "No hay registros en la base de datos" : "Ok")
                            .object(horariosDto)
                            .build()
            );
        } catch (DataAccessException ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(MensajeResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build());
        }
    }

    @PostMapping("horario")
    public ResponseEntity<?> guardarHorario(@RequestBody HorarioDto horarioDto) {
        try {
            Horario horario = horarioService.guardar(horarioDto);

            return ResponseEntity.created(URI.create("/api/v1/horario"))
                    .body(MensajeResponse.builder()
                            .mensaje("Se registró el horario correctamente")
                            .object(HorarioDto.builder()
                                    .idHorario(horario.getIdHorario())
                                    .fecha(horario.getFecha())
                                    .horaInicio(horario.getHoraInicio())
                                    .horaFin(horario.getHoraFin())
                                    .estado(horario.isEstado())
                                    .odontologo(new Odontologo(
                                            horario.getOdontologo().getIdOdontologo()))
                                    .usuario(new Usuario(
                                            horario.getUsuario().getIdUsuario()))
                                    .build()
                            ).build()
                    );
        } catch (DataAccessException ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(MensajeResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build());
        }
    }

    @PutMapping("horario/{id}")
    public ResponseEntity<?> actualizarHorario(@RequestBody HorarioDto horarioDto, @PathVariable Integer id) {
        try {
            if (horarioService.existePorId(id)) {
                horarioDto.setIdHorario(id);
                Horario horarioUpdate = horarioService.guardar(horarioDto);

                return ResponseEntity.ok(
                        MensajeResponse.builder()
                                .mensaje("Se actualizó correctamente el horario")
                                .object(HorarioDto.builder()
                                        .idHorario(horarioUpdate.getIdHorario())
                                        .fecha(horarioUpdate.getFecha())
                                        .horaInicio(horarioUpdate.getHoraInicio())
                                        .horaFin(horarioUpdate.getHoraFin())
                                        .estado(horarioUpdate.isEstado())
                                        .odontologo(new Odontologo(
                                                horarioUpdate.getOdontologo().getIdOdontologo()
                                        ))
                                        .usuario(new Usuario(
                                                horarioUpdate.getUsuario().getIdUsuario()
                                        ))
                                        .build())
                                .build());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(MensajeResponse.builder()
                            .mensaje("No existe un horario con el ID ingresado")
                            .object(null)
                            .build());
        } catch (DataAccessException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(MensajeResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build());
        }
    }

    @DeleteMapping("horario/{id}")
    public ResponseEntity<?> eliminarHorario(@PathVariable Integer id) {
        try {
            if (horarioService.existePorId(id)) {
                horarioService.eliminarPorId(id);
                return ResponseEntity.ok(
                        MensajeResponse.builder()
                                .mensaje("Se eliminó correctamente el horario")
                                .object(null)
                                .build()
                );
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(MensajeResponse.builder()
                            .mensaje("No existe un horario con el ID ingresado")
                            .object(null)
                            .build());
        } catch (DataAccessException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(MensajeResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build());
        }
    }
}
