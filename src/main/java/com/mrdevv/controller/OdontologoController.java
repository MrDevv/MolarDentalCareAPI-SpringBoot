package com.mrdevv.controller;

import com.mrdevv.model.dto.OdontologoDto;
import com.mrdevv.model.entity.Odontologo;
import com.mrdevv.model.payload.MensajeResponse;
import com.mrdevv.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class OdontologoController {

    @Autowired
    IOdontologoService odontologoService;

    @GetMapping("odontologos")
    public ResponseEntity<?> listarOdontologos() {
        try {
            List<Odontologo> odontologos = odontologoService.listarTodos();

            if (odontologos.isEmpty()) {
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("No hay registros en la base de datos")
                                .object(null)
                                .build()
                        , HttpStatus.OK
                );
            }

            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Ok")
                            .object(odontologos)
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

    @PostMapping("odontologo")
    public ResponseEntity<?> guardarOdontologo(@RequestBody OdontologoDto odontologoDto) {
        try {
            Odontologo odontologoCreate = odontologoService.guardar(odontologoDto);
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Se registró el odontologo correctamente")
                            .object(OdontologoDto.builder()
                                    .idOdontologo(odontologoCreate.getIdOdontologo())
                                    .apellido(odontologoCreate.getApellido())
                                    .nombre(odontologoCreate.getNombre())
                                    .dni(odontologoCreate.getDni())
                                    .email(odontologoCreate.getEmail())
                                    .direccion(odontologoCreate.getDireccion())
                                    .telefono(odontologoCreate.getTelefono())
                                    .fechaDeNacimiento(odontologoCreate.getFechaDeNacimiento())
                                    .estado(odontologoCreate.getEstado())
                                    .build())
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

    @PutMapping("odontologo/{id}")
    public ResponseEntity<?> actualizarOdontologo(@PathVariable Integer id, @RequestBody OdontologoDto odontologoDto) {
        try {
            if (odontologoService.existePorId(id)) {
                odontologoDto.setIdOdontologo(id);
                Odontologo odontologoUpdate = odontologoService.guardar(odontologoDto);
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("Se actualizó el odontologo correctamente")
                                .object(OdontologoDto.builder()
                                        .idOdontologo(odontologoUpdate.getIdOdontologo())
                                        .apellido(odontologoUpdate.getApellido())
                                        .nombre(odontologoUpdate.getNombre())
                                        .dni(odontologoUpdate.getDni())
                                        .email(odontologoUpdate.getEmail())
                                        .direccion(odontologoUpdate.getDireccion())
                                        .telefono(odontologoUpdate.getTelefono())
                                        .fechaDeNacimiento(odontologoUpdate.getFechaDeNacimiento())
                                        .estado(odontologoUpdate.getEstado())
                                        .build())
                                .build()
                        , HttpStatus.CREATED
                );
            }

            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No existe el odontologo con el ID ingresado")
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

    @DeleteMapping("odontologo/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Integer id) {
        try {
            if (odontologoService.existePorId(id)) {
                odontologoService.eliminarPorId(id);
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("Se eliminó correctamente el odontologo")
                                .object(null)
                                .build()
                        , HttpStatus.OK
                );
            }

            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No existe el odontologo con el ID ingresado")
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
