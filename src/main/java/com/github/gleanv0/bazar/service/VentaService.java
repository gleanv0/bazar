package com.github.gleanv0.bazar.service;

import com.github.gleanv0.bazar.model.Venta;
import com.github.gleanv0.bazar.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VentaService implements IService<Long, Venta> {

    @Autowired
    private IVentaRepository ventaRepository;

    @Override
    public Optional<Venta> get(Long id) {
        return ventaRepository.findById(id);
    }

    @Override
    public List<Venta> getAll() {
        return ventaRepository.findAll();
    }

    @Override
    public Optional<Venta> edit(Long id, Venta entity) {
        boolean exists = get(id).isPresent();
        if (exists && Objects.equals(entity.getCodigo(), id)) {
            return Optional.of(save(entity));
        }
        return Optional.empty();
    }

    @Override
    public Venta save(Venta entity) {
        return ventaRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        ventaRepository.deleteById(id);
    }
}
