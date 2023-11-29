package com.mrdevv.service.impl;

import com.mrdevv.model.dao.CitaDAO;
import com.mrdevv.model.dto.CitaDto;
import com.mrdevv.model.entity.Cita;
import com.mrdevv.service.ICitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CitaImplService implements ICitaService {
    @Autowired
    CitaDAO citaDAO;

    @Transactional(readOnly = true)
    @Override
    public List<Cita> listarTodos() {
        return (List<Cita>) citaDAO.findAll();
    }

    @Transactional
    @Override
    public Cita guardar(CitaDto citaDto) {
        return citaDAO.save(Cita.builder()
                .idCita(citaDto.getIdCita())
                .estado(citaDto.getEstado())
                .horario(citaDto.getHorario())
                .paciente(citaDto.getPaciente())
                .usuario(citaDto.getUsuario())
                .build());
    }

    @Transactional
    @Override
    public void eliminarPorId(Integer id) {
        citaDAO.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existePorId(Integer id) {
        return citaDAO.existsById(id);
    }
}
