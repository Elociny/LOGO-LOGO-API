package com.logologo.api.controller;

import com.logologo.api.model.Comprar;
import com.logologo.api.service.ComprarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compras")
@RequiredArgsConstructor
public class ComprarController {

    private final ComprarService comprarService;

    @GetMapping
    public ResponseEntity<List<Comprar>> listar() {
        return ResponseEntity.ok(comprarService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<Comprar> salvar(@RequestBody Comprar compra) {
        return ResponseEntity.ok(comprarService.salvar(compra));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comprar> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(comprarService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        comprarService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
