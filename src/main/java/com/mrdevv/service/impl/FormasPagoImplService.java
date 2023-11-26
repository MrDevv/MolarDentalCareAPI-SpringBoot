package com.mrdevv.service.impl;

import com.mrdevv.model.dao.FormasPagoDAO;
import com.mrdevv.model.entity.FormasPago;
import com.mrdevv.service.IFormasPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FormasPagoImplService implements IFormasPagoService {

    @Autowired
    FormasPagoDAO formasPagoDAO;

    @Transactional(readOnly = true)
    @Override
    public List<FormasPago> listarTodos() {
        return (List<FormasPago>) formasPagoDAO.findAll();
    }
}
