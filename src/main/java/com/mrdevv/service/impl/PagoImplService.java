package com.mrdevv.service.impl;

import com.mrdevv.model.dao.PagoDAO;
import com.mrdevv.model.dto.PagoDto;
import com.mrdevv.model.entity.Pago;
import com.mrdevv.service.IPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PagoImplService implements IPagoService {

    @Autowired
    PagoDAO pagoDAO;

    @Transactional(readOnly = true)
    @Override
    public List<Pago> listarTodos() {
        return (List<Pago>) pagoDAO.findAll();
    }

    @Transactional
    @Override
    public Pago guardar(PagoDto pagoDto) {
        return pagoDAO.save(Pago.builder()
                .idPagos(pagoDto.getIdPagos())
                .fechaPago(pagoDto.getFechaPago())
                .montoTotal(pagoDto.getMontoTotal())
                .estado(pagoDto.getEstado())
                .cita(pagoDto.getCita())
                .formasPago(pagoDto.getFormasPago())
                .build());
    }

    @Transactional
    @Override
    public void eliminarPorId(Integer id) {
        pagoDAO.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existePorId(Integer id) {
        return pagoDAO.existsById(id);
    }
}
