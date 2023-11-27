package com.mrdevv.controller;

import com.mrdevv.model.dto.HorarioDto;
import com.mrdevv.model.entity.Horario;
import com.mrdevv.model.payload.MensajeResponse;
import com.mrdevv.service.IHorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class HorarioController {
    @Autowired
    IHorarioService horarioService;

    @GetMapping("horarios")
    public ResponseEntity<?> listarHorarios(){
        try {
            List<Horario> horarios = horarioService.listarTodos();

            if (horarios.isEmpty()){
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("No hay registros en la base de datos")
                                .object(horarios)
                                .build()
                        , HttpStatus.OK
                );
            }
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Ok")
                            .object(horarios)
                            .build()
                    , HttpStatus.OK
            );
        }catch (DataAccessException ex){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PostMapping("horario")
    public ResponseEntity<?> guardarHorario(@RequestBody HorarioDto horarioDto) {
        try {
            Horario horario = horarioService.guardar(horarioDto);

            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Se registró el horario correctamente")
                            .object(HorarioDto.builder()
                                    .idHorario(horario.getIdHorario())
                                    .idOdontologo(horario.getIdOdontologo())
                                    .idUsuario(horario.getIdUsuario())
                                    .fecha(horario.getFecha())
                                    .horaInicio(horario.getHoraInicio())
                                    .horaFin(horario.getHoraFin())
                                    .estado(horario.isEstado())
                                    .build()
                            )
                            .build()
                    , HttpStatus.CREATED
            );

        }catch (DataAccessException ex){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @PutMapping("horario/{id}")
    public ResponseEntity<?> actualizarHorario(@RequestBody HorarioDto horarioDto, @PathVariable Integer id){
        try {
            if (horarioService.existePorId(id)){
                horarioDto.setIdHorario(id);
                Horario horarioUpdate = horarioService.guardar(horarioDto);
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("Se actualizó correctamente el horario")
                                .object(Horario.builder()
                                        .idHorario(horarioUpdate.getIdHorario())
                                        .idOdontologo(horarioUpdate.getIdOdontologo())
                                        .idUsuario(horarioUpdate.getIdUsuario())
                                        .fecha(horarioUpdate.getFecha())
                                        .horaInicio(horarioUpdate.getHoraInicio())
                                        .horaFin(horarioUpdate.getHoraFin())
                                        .estado(horarioUpdate.isEstado())
                                        .build())
                                .build()
                        , HttpStatus.OK
                );
            }
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No existe un horario con el ID ingresado")
                            .object(null)
                            .build()
                    , HttpStatus.INTERNAL_SERVER_ERROR
            );
        }catch (DataAccessException ex){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @DeleteMapping("horario/{id}")
    public ResponseEntity<?> eliminarHorario(@PathVariable Integer id){
        try {
            if (horarioService.existePorId(id)){
                horarioService.eliminarPorId(id);
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("Se eliminó correctamente el horario")
                                .object(null)
                                .build()
                        , HttpStatus.OK
                );
            }
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No existe un horario con el ID ingresado")
                            .object(null)
                            .build()
                    , HttpStatus.OK
            );
        }catch (DataAccessException ex){
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
