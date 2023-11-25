package com.mrdevv.model.dao;

import com.mrdevv.model.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioDAO extends CrudRepository<Usuario, Integer> {
}
