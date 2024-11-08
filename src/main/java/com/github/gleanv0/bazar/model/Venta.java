package com.github.gleanv0.bazar.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codigo;
    @Column(nullable = false)
    private LocalDate fecha;
    @Column(nullable = false)
    private Double total;
    @ManyToOne
    private Cliente cliente;
    @ManyToMany
    private List<Producto> productos;
}
