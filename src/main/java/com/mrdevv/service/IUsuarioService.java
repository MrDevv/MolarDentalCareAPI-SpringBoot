package com.mrdevv.service;

import com.mrdevv.model.dto.UsuarioDto;
import com.mrdevv.model.entity.Usuario;

import java.util.List;

public interface IUsuarioService {

    List<Usuario> listarTodos();

    Usuario guardar(UsuarioDto usuarioDto);

    void eliminarPorId(Integer id);

    boolean existePorId(Integer id);
}
