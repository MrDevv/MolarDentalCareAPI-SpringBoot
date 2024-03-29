package com.mrdevv.model.dao;

import com.mrdevv.model.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioDAO extends CrudRepository<Usuario, Integer> {

    Optional<Usuario> findUsuarioByUsuario(String usuario);
}
