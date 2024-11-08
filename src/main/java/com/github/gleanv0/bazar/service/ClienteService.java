package com.github.gleanv0.bazar.service;

import com.github.gleanv0.bazar.model.Cliente;
import com.github.gleanv0.bazar.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteService implements IService<Long, Cliente> {

    @Autowired
    private IClienteRepository clienteRepository;

    @Override
    public Optional<Cliente> get(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> edit(Long id, Cliente entity) {
        boolean exists = get(id).isPresent();
        if (exists && Objects.equals(entity.getId(), id)) {
            return Optional.of(save(entity));
        }
        return Optional.empty();
    }

    @Override
    public Cliente save(Cliente entity) {
        return clienteRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}
