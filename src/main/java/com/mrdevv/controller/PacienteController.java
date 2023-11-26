package com.mrdevv.controller;

import com.mrdevv.model.dto.PacienteDto;
import com.mrdevv.model.entity.Paciente;
import com.mrdevv.model.payload.MensajeResponse;
import com.mrdevv.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PacienteController {

    @Autowired
    IPacienteService pacienteService;

    @GetMapping("pacientes")
    public ResponseEntity<?> listarPacientes(){
        try {
            List<Paciente> pacientes = pacienteService.listarTodos();

            if (pacientes.isEmpty()){
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("No hay registros en la base de datos")
                                .object(pacientes)
                                .build()
                        , HttpStatus.OK
                );
            }

            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Ok")
                            .object(pacientes)
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

    @PostMapping("paciente")
    public ResponseEntity<?> guardarPaciente(@RequestBody PacienteDto pacienteDto){
        try {
            Paciente paciente = pacienteService.guardar(pacienteDto);
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Se registró el paciente correctamente")
                            .object(PacienteDto.builder()
                                    .idPaciente(paciente.getIdPaciente())
                                    .apellido(paciente.getApellido())
                                    .nombre(paciente.getNombre())
                                    .dni(paciente.getDni())
                                    .email(paciente.getEmail())
                                    .direccion(paciente.getDireccion())
                                    .telefono(paciente.getTelefono())
                                    .fechaDeNacimiento(paciente.getFechaDeNacimiento())
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

    @PutMapping("paciente/{id}")
    public ResponseEntity<?> actualizarPaciente(@PathVariable Integer id, @RequestBody PacienteDto pacienteDto){
        try {
            if (pacienteService.existePorId(id)){
                pacienteDto.setIdPaciente(id);
                Paciente pacienteUpdate = pacienteService.guardar(pacienteDto);
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("Se actualizó el paciente correctamente")
                                .object(PacienteDto.builder()
                                        .idPaciente(pacienteUpdate.getIdPaciente())
                                        .apellido(pacienteUpdate.getApellido())
                                        .nombre(pacienteUpdate.getNombre())
                                        .dni(pacienteUpdate.getDni())
                                        .email(pacienteUpdate.getEmail())
                                        .direccion(pacienteUpdate.getDireccion())
                                        .telefono(pacienteUpdate.getTelefono())
                                        .fechaDeNacimiento(pacienteUpdate.getFechaDeNacimiento())
                                        .build()
                                )
                                .build()
                        , HttpStatus.OK
                );
            }

            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No existe un registro con el ID ingresado")
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

    @DeleteMapping("paciente/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Integer id){
        try {
            if (pacienteService.existePorId(id)){
                pacienteService.eliminarPorId(id);

                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("Se eliminó el paciente correctamente")
                                .object(null)
                                .build()
                        , HttpStatus.OK
                );
            }

            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No existe un registro con el ID ingresado")
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
