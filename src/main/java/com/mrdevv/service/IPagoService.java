package com.mrdevv.service;

import com.mrdevv.model.dto.PagoDto;
import com.mrdevv.model.entity.Pago;

import java.util.List;

public interface IPagoService {

    List<Pago> listarTodos();

    Pago guardar(PagoDto pagoDto);

    void eliminarPorId(Integer id);

    boolean existePorId(Integer id);
}
