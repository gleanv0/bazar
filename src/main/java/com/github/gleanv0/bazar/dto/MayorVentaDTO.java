package com.github.gleanv0.bazar.dto;

import com.github.gleanv0.bazar.model.Cliente;
import com.github.gleanv0.bazar.model.Venta;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MayorVentaDTO {
    private Long codigoVenta;
    private String nombreCliente;
    private String apellidoCliente;
    private Integer cantidad;
    private Double total;

    public static class Mapper {
        public static MayorVentaDTO toDTO(Venta mayorVenta, Cliente cliente) {
            return new MayorVentaDTO(mayorVenta.getCodigo(), cliente.getNombre(), cliente.getApellido(), mayorVenta.getProductos().size(), mayorVenta.getTotal());
        }
    }
}
