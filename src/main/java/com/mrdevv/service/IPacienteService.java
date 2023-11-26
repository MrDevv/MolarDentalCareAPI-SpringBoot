package com.mrdevv.service;

import com.mrdevv.model.dto.PacienteDto;
import com.mrdevv.model.entity.Paciente;

import java.util.List;

public interface IPacienteService {

    List<Paciente> listarTodos();

    Paciente guardar(PacienteDto pacienteDto);

    void eliminarPorId(Integer id);

    boolean existePorId(Integer id);
}
