package com.github.gleanv0.bazar.dto;

import com.github.gleanv0.bazar.model.Venta;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class VentasDiariasDTO {
    private Double total;
    private Integer cantidad;

    public static class Mapper {
        public static VentasDiariasDTO toDTO(List<Venta> ventas) {
            return new VentasDiariasDTO(ventas.stream().mapToDouble(Venta::getTotal).sum(), ventas.size());
        }
    }
}