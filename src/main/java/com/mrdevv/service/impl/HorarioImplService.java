package com.mrdevv.service.impl;

import com.mrdevv.model.dao.HorarioDAO;
import com.mrdevv.model.dto.HorarioDto;
import com.mrdevv.model.entity.Horario;
import com.mrdevv.service.IHorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HorarioImplService implements IHorarioService {

    @Autowired
    HorarioDAO horarioDAO;

    @Transactional(readOnly = true)
    @Override
    public List<Horario> listarTodos() {
        return (List<Horario>) horarioDAO.findAll();
    }

    @Transactional
    @Override
    public Horario guardar(HorarioDto horarioDto) {
        return horarioDAO.save(Horario.builder()
                .idHorario(horarioDto.getIdHorario())
                .idOdontologo(horarioDto.getIdOdontologo())
                .idUsuario(horarioDto.getIdUsuario())
                .fecha(horarioDto.getFecha())
                .horaInicio(horarioDto.getHoraInicio())
                .horaFin(horarioDto.getHoraFin())
                .estado(horarioDto.isEstado())
                .build());
    }

    @Transactional
    @Override
    public void eliminarPorId(Integer id) {
        horarioDAO.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existePorId(Integer id) {
        return horarioDAO.existsById(id);
    }
}
