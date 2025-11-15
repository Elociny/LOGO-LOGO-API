package com.logologo.api.controller;

import com.logologo.api.dto.ComprarRequestDTO;
import com.logologo.api.dto.ComprarResponseDTO;
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
    public ResponseEntity<List<ComprarResponseDTO>> listar() {
        return ResponseEntity.ok(comprarService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComprarResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(comprarService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ComprarResponseDTO> salvar(@RequestBody ComprarRequestDTO dto) {
        return ResponseEntity.ok(comprarService.salvar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        comprarService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
