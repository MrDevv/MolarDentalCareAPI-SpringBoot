package com.mrdevv.service;

import com.mrdevv.model.dto.CitaDto;
import com.mrdevv.model.entity.Cita;

import java.util.List;

public interface ICitaService {

    List<Cita> listarTodos();

    Cita guardar(CitaDto citaDto);

    void eliminarPorId(Integer id);

    boolean existePorId(Integer id);
}
