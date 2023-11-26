package com.mrdevv.service.impl;

import com.mrdevv.model.dao.PacienteDAO;
import com.mrdevv.model.dto.PacienteDto;
import com.mrdevv.model.entity.Paciente;
import com.mrdevv.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PacienteImplService implements IPacienteService {

    @Autowired
    PacienteDAO pacienteDAO;

    @Transactional(readOnly = true)
    @Override
    public List<Paciente> listarTodos() {
        return (List<Paciente>) pacienteDAO.findAll();
    }

    @Transactional
    @Override
    public Paciente guardar(PacienteDto pacienteDto) {
        return pacienteDAO.save(Paciente.builder()
                .idPaciente(pacienteDto.getIdPaciente())
                .apellido(pacienteDto.getApellido())
                .nombre(pacienteDto.getNombre())
                .dni(pacienteDto.getDni())
                .email(pacienteDto.getEmail())
                .direccion(pacienteDto.getDireccion())
                .telefono(pacienteDto.getTelefono())
                .fechaDeNacimiento(pacienteDto.getFechaDeNacimiento())
                .build()
        );
    }

    @Transactional
    @Override
    public void eliminarPorId(Integer id) {
        pacienteDAO.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existePorId(Integer id) {
        return pacienteDAO.existsById(id);
    }
}
