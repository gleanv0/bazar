package com.github.gleanv0.bazar.repository;

import com.github.gleanv0.bazar.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente, Long> {}
