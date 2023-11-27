package com.mrdevv.service;

import com.mrdevv.model.dto.HorarioDto;
import com.mrdevv.model.entity.Horario;

import java.util.List;

public interface IHorarioService {
    List<Horario> listarTodos();

    Horario guardar(HorarioDto horarioDto);

    void eliminarPorId(Integer id);

    boolean existePorId(Integer id);
}
