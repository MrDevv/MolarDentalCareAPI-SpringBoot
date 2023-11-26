package com.mrdevv.service.impl;

import com.mrdevv.model.dao.OdontologoDAO;
import com.mrdevv.model.dto.OdontologoDto;
import com.mrdevv.model.entity.Odontologo;
import com.mrdevv.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OdontologoImplService implements IOdontologoService {

    @Autowired
    OdontologoDAO odontologoDAO;

    @Transactional(readOnly = true)
    @Override
    public List<Odontologo> listarTodos() {
        return (List<Odontologo>) odontologoDAO.findAll();
    }

    @Transactional
    @Override
    public Odontologo guardar(OdontologoDto odontologoDto) {
        return odontologoDAO.save(Odontologo.builder()
                .idOdontologo(odontologoDto.getIdOdontologo())
                .apellido(odontologoDto.getApellido())
                .nombre(odontologoDto.getNombre())
                .dni(odontologoDto.getDni())
                .email(odontologoDto.getEmail())
                .direccion(odontologoDto.getDireccion())
                .telefono(odontologoDto.getTelefono())
                .fechaDeNacimiento(odontologoDto.getFechaDeNacimiento())
                .estado(odontologoDto.isEstado())
                .build()
        );
    }

    @Transactional
    @Override
    public void eliminarPorId(Integer id) {
        odontologoDAO.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override()
    public boolean existePorId(Integer id) {
        return odontologoDAO.existsById(id);
    }
}
