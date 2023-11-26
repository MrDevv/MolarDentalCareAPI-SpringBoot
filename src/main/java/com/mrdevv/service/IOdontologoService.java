package com.mrdevv.service;

import com.mrdevv.model.dto.OdontologoDto;
import com.mrdevv.model.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {

    List<Odontologo> listarTodos();

    Odontologo guardar(OdontologoDto odontologoDto);

    void eliminarPorId(Integer id);

    boolean existePorId(Integer id);
}
