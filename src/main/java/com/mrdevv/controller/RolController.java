package com.mrdevv.controller;

import com.mrdevv.model.dto.RolDto;
import com.mrdevv.model.entity.Rol;
import com.mrdevv.model.payload.MensajeResponse;
import com.mrdevv.service.impl.RolImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RolController {

    @Autowired
    RolImplService rolService;

    @GetMapping("roles")
    public ResponseEntity<?> listarRoles() {
        try {
            List<Rol> roles = rolService.listarTodos();
            if (roles.isEmpty()) {
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("No hay registros en la base de datos")
                                .object(null)
                                .build()
                        , HttpStatus.OK);
            } else {
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("Ok")
                                .object(roles)
                                .build()
                        , HttpStatus.OK);
            }
        }catch (DataAccessException ex){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(ex.getMessage())
                    .object(null)
                    .build()
            , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("rol")
    public ResponseEntity<?> guardarRol(@RequestBody RolDto rolDto){
        Rol rol = null;
        try {
            rol = rolService.guardar(Rol.builder().descripcion(rolDto.getDescripcion()).build());
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Se guardó correctamente")
                            .object(RolDto.builder()
                                    .idRol(rol.getIdRol())
                                    .descripcion(rol.getDescripcion())
                                    .build())
                            .build()
                    , HttpStatus.OK);
        }catch (DataAccessException ex){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("rol/{id}")
    public ResponseEntity<?> eliminarRolPorId(@PathVariable Integer id){
        try {
            if (rolService.existeRolPorId(id)){
                rolService.eliminarPorId(id);
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("Ok")
                                .object(null)
                                .build()
                , HttpStatus.OK);
            }

            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No se encontró un rol con el ID")
                            .object(null)
                            .build()
                    , HttpStatus.OK);

        }catch (DataAccessException ex){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("rol/{id}")
    public ResponseEntity<?> actualizarRol(@RequestBody RolDto rolDto, @PathVariable Integer id){
        Rol rol = null;
        try {
            if (rolService.existeRolPorId(id)){
                rolDto.setIdRol(id);
                rol = rolService.guardar(Rol.builder().idRol(rolDto.getIdRol()).descripcion(rolDto.getDescripcion()).build());
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("Se actualizó correctamente el rol")
                                .object(RolDto.builder()
                                        .idRol(rol.getIdRol())
                                        .descripcion(rol.getDescripcion())
                                        .build())
                                .build()
                        , HttpStatus.OK);
            }

            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No existe un registro con el ID recibido")
                            .object(null)
                            .build()
                    , HttpStatus.OK);

        }catch (DataAccessException ex){
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje(ex.getMessage())
                            .object(null)
                            .build()
                    , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
