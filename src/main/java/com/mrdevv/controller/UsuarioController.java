package com.mrdevv.controller;

import com.mrdevv.model.dto.UsuarioDto;
import com.mrdevv.model.entity.Usuario;
import com.mrdevv.model.payload.MensajeResponse;
import com.mrdevv.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class UsuarioController {

    @Autowired
    IUsuarioService usuarioService;

    @GetMapping("usuarios")
    public ResponseEntity<?> listarUsuarios() {
        try {
            List<Usuario> usuarios = usuarioService.listarTodos();

            if (usuarios.isEmpty()) {
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
                            .object(usuarios)
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

    @PostMapping("usuario")
    public ResponseEntity<?> guardarUsuario(@RequestBody UsuarioDto usuarioDto) {
        try {
            Usuario usuario = usuarioService.guardar(usuarioDto);
            
            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Se guardó correctamente el usuario")
                            .object(UsuarioDto.builder()
                                    .idUsuario(usuario.getIdUsuario())
                                    .idRol(usuario.getIdRol())
                                    .apellido(usuario.getApellido())
                                    .nombre(usuario.getNombre())
                                    .dni(usuario.getDni())
                                    .email(usuario.getEmail())
                                    .telefono(usuario.getTelefono())
                                    .usuario(usuario.getUsuario())
                                    .password(usuario.getPassword())
                                    .estado(usuario.isEstado())
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

    @PutMapping("usuario/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Integer id, @RequestBody UsuarioDto usuarioDto){
        try {
            if (usuarioService.existePorId(id)){
                usuarioDto.setIdUsuario(id);
                Usuario usuarioUpdate = usuarioService.guardar(usuarioDto);

                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("Se actualizó correctamente el usuario")
                                .object(UsuarioDto.builder()
                                        .idUsuario(usuarioUpdate.getIdUsuario())
                                        .idRol(usuarioUpdate.getIdRol())
                                        .apellido(usuarioUpdate.getApellido())
                                        .nombre(usuarioUpdate.getNombre())
                                        .dni(usuarioUpdate.getDni())
                                        .email(usuarioUpdate.getEmail())
                                        .telefono(usuarioUpdate.getTelefono())
                                        .usuario(usuarioUpdate.getUsuario())
                                        .password(usuarioUpdate.getPassword())
                                        .estado(usuarioUpdate.isEstado())
                                        .build())
                                .build()
                        , HttpStatus.OK
                );
            }

            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No existe el usuario con el ID ingresado")
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

    @DeleteMapping("usuario/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Integer id){
        try {
            if (usuarioService.existePorId(id)){
                usuarioService.eliminarPorId(id);

                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("Se eliminó correctamente el usuario")
                                .object(null)
                                .build()
                        , HttpStatus.OK
                );
            }

            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("No existe un usuario con el ID ingresado")
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
