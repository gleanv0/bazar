package com.github.gleanv0.bazar.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class VentaDTO {
    private Long clienteId;
    private LocalDate fecha;
    private List<Long> productos;
}
