package com.github.gleanv0.bazar.repository;

import com.github.gleanv0.bazar.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVentaRepository extends JpaRepository<Venta, Long> {}