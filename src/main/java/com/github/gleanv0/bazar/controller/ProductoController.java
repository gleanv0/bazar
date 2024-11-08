package com.github.gleanv0.bazar.controller;

import com.github.gleanv0.bazar.model.Producto;
import com.github.gleanv0.bazar.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/todos")
    public List<Producto> getAll() {
        return productoService.getAll();
    }

    @GetMapping
    public Producto getById(@RequestParam("id") Long id) {
        return productoService.get(id).orElse(null);
    }

    @PostMapping
    public Producto create(@RequestBody Producto producto) {
        return productoService.save(producto);
    }

    @PostMapping("/multiple")
    public List<Producto> createMultiple(@RequestBody List<Producto> productos) {
        return productoService.saveMultiple(productos);
    }

    @PutMapping("/{id}")
    public Producto update(@RequestBody Producto producto, @PathVariable("id") Long id) {
        return productoService.edit(id, producto).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        productoService.delete(id);
    }

    @GetMapping("/stock_insuficiente")
    public List<Producto> getStockInsuficiente() {
        return productoService.getAll().stream().filter(producto -> producto.getCantidadDisponible() < 5).toList();
    }

}
