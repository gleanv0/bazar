package com.github.gleanv0.bazar.controller;

import com.github.gleanv0.bazar.dto.MayorVentaDTO;
import com.github.gleanv0.bazar.dto.VentaDTO;
import com.github.gleanv0.bazar.dto.VentasDiariasDTO;
import com.github.gleanv0.bazar.model.Cliente;
import com.github.gleanv0.bazar.model.Producto;
import com.github.gleanv0.bazar.model.Venta;
import com.github.gleanv0.bazar.service.ClienteService;
import com.github.gleanv0.bazar.service.ProductoService;
import com.github.gleanv0.bazar.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/venta")
public class VentaController {

    @Autowired
    private VentaService ventaService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ProductoService productoService;

    @GetMapping("/todos")
    public List<Venta> getAll() {
        return ventaService.getAll();
    }

    @GetMapping
    public Venta getById(@RequestParam("id") Long id) {
        return ventaService.get(id).orElse(null);
    }

    @PostMapping
    public Venta create(@RequestBody VentaDTO ventaDTO) {
        Optional<Cliente> cliente = clienteService.get(ventaDTO.getClienteId());
        List<Producto> productos = ventaDTO.getProductos().stream()
                .map((id) -> productoService.get(id).orElse(null))
                .filter(Objects::nonNull)
                .toList();
        double total = productos.stream().mapToDouble(Producto::getCosto).sum();

        if (cliente.isEmpty() || productos.isEmpty()) {
            return null;
        }

        Venta venta = new Venta();
        venta.setFecha(ventaDTO.getFecha());
        venta.setCliente(cliente.get());
        venta.setTotal(total);
        venta.setProductos(productos);
        return ventaService.save(venta);
    }

    @PutMapping("/{id}")
    public Venta update(@RequestBody Venta venta, @PathVariable("id") Long id) {
        return ventaService.edit(id, venta).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        ventaService.delete(id);
    }

    @GetMapping("/productos/{codigo_venta}")
    public List<Producto> getProductos(@PathVariable("codigo_venta") Long codigoVenta) {
        Optional<Venta> venta = ventaService.get(codigoVenta);
        return venta.map(Venta::getProductos).orElse(null);
    }

    @GetMapping("/fecha")
    public VentasDiariasDTO getByFecha(@RequestParam("fecha") LocalDate fechaVenta) {
        return VentasDiariasDTO.Mapper.toDTO(ventaService.getAll().stream().filter(venta -> venta.getFecha().equals(fechaVenta)).toList());
    }

    @GetMapping("/mayor_venta")
    public MayorVentaDTO getMayorVenta() {
        var mayorVenta = ventaService.getAll().stream().max(Comparator.comparing(Venta::getTotal));

        if (mayorVenta.isEmpty()) {
            return null;
        }

        var cliente = clienteService.get(mayorVenta.get().getCliente().getId());

        return cliente.map(value -> MayorVentaDTO.Mapper.toDTO(mayorVenta.get(), value)).orElse(null);
    }

}
