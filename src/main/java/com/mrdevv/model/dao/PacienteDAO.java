package com.mrdevv.model.dao;

import com.mrdevv.model.entity.Paciente;
import org.springframework.data.repository.CrudRepository;

public interface PacienteDAO extends CrudRepository<Paciente, Integer> {
}
