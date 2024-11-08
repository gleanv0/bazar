package com.github.gleanv0.bazar.service;

import com.github.gleanv0.bazar.model.Producto;
import com.github.gleanv0.bazar.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductoService implements IService<Long, Producto> {

    @Autowired
    private IProductoRepository productoRepository;

    @Override
    public Optional<Producto> get(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public List<Producto> getAll() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> edit(Long id, Producto entity) {
        boolean exists = get(id).isPresent();
        if (exists && Objects.equals(entity.getCodigo(), id)) {
            return Optional.of(save(entity));
        }
        return Optional.empty();
    }

    @Override
    public Producto save(Producto entity) {
        return productoRepository.save(entity);
    }

    public List<Producto> saveMultiple(List<Producto> productos) {
        return productoRepository.saveAll(productos);
    }

    @Override
    public void delete(Long id) {
        productoRepository.deleteById(id);
    }
}
