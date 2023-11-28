package com.mrdevv.service.impl;

import com.mrdevv.model.dao.UsuarioDAO;
import com.mrdevv.model.dto.UsuarioDto;
import com.mrdevv.model.entity.Usuario;
import com.mrdevv.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioImplService implements IUsuarioService {

    @Autowired
    UsuarioDAO usuarioDAO;

    @Transactional(readOnly = true)
    @Override
    public List<Usuario> listarTodos() {
        return (List<Usuario>) usuarioDAO.findAll();
    }

    @Transactional
    @Override
    public Usuario guardar(UsuarioDto usuarioDto) {
        return usuarioDAO.save(Usuario.builder()
                .idUsuario(usuarioDto.getIdUsuario())
                .apellido(usuarioDto.getApellido())
                .nombre(usuarioDto.getNombre())
                .dni(usuarioDto.getDni())
                .email(usuarioDto.getEmail())
                .telefono(usuarioDto.getTelefono())
                .usuario(usuarioDto.getUsuario())
                .password(usuarioDto.getPassword())
                .estado(usuarioDto.isEstado())
                .rol(usuarioDto.getRol())
                .build());
    }

    @Transactional
    @Override
    public void eliminarPorId(Integer id) {
        usuarioDAO.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existePorId(Integer id) {
        return usuarioDAO.existsById(id);
    }
}
