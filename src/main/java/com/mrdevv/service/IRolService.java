package com.mrdevv.service;

import com.mrdevv.model.entity.Rol;

import java.util.List;

public interface IRolService {
    List<Rol> listarTodos();

    Rol guardar(Rol obj);

    void eliminarPorId(Integer id);

    boolean existeRolPorId(Integer id);

}
