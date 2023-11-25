package com.mrdevv.service.impl;

import com.mrdevv.model.dao.RolDAO;
import com.mrdevv.model.entity.Rol;
import com.mrdevv.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolImplService implements IRolService {

    @Autowired
    RolDAO rolDao;

    @Transactional(readOnly = true)
    @Override
    public List<Rol> listarTodos() {
        return (List<Rol>) rolDao.findAll();
    }

    @Transactional
    @Override
    public Rol guardar(Rol rol){
        return rolDao.save(rol);
    }

    @Transactional
    @Override
    public void eliminarPorId(Integer id) {
        rolDao.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existeRolPorId(Integer id) {
        return rolDao.existsById(id);
    }
}
