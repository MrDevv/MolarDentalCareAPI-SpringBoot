package com.mrdevv.controller;

import com.mrdevv.model.entity.FormasPago;
import com.mrdevv.model.payload.MensajeResponse;
import com.mrdevv.service.IFormasPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class FormasPagoController {

    @Autowired
    IFormasPagoService formasPagoService;

    @GetMapping("formaspago")
    public ResponseEntity<?> listarFormasPago(){
        try {
            List<FormasPago> formasPagos = formasPagoService.listarTodos();

            if (formasPagos.isEmpty()){
                return new ResponseEntity<>(
                        MensajeResponse.builder()
                                .mensaje("No hay registros en la base de datos")
                                .object(formasPagos)
                                .build()
                        , HttpStatus.OK
                );
            }

            return new ResponseEntity<>(
                    MensajeResponse.builder()
                            .mensaje("Ok")
                            .object(formasPagos)
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
