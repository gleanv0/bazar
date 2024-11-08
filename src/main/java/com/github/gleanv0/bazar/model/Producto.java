package com.github.gleanv0.bazar.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codigo;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String marca;
    @Column(nullable = false)
    private Double costo;
    @Column(nullable = false, name = "cantidad_disponible")
    private Integer cantidadDisponible;
}
