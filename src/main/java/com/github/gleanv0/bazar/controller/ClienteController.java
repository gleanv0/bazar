package com.github.gleanv0.bazar.controller;

import com.github.gleanv0.bazar.model.Cliente;
import com.github.gleanv0.bazar.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/todos")
    public List<Cliente> getAll() {
        return clienteService.getAll();
    }

    @GetMapping
    public Cliente getById(@RequestParam("id") Long id) {
        return clienteService.get(id).orElse(null);
    }

    @PostMapping
    public Cliente create(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }

    @PutMapping("/{id}")
    public Cliente update(@RequestBody Cliente cliente, @PathVariable("id") Long id) {
        return clienteService.edit(id, cliente).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        clienteService.delete(id);
    }
    
}
