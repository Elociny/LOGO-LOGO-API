package com.logologo.api.controller;

import com.logologo.api.dto.AvaliacaoRequestDTO;
import com.logologo.api.dto.AvaliacaoResponseDTO;
import com.logologo.api.service.AvaliacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacoes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AvaliacaoController {

    private final AvaliacaoService service;

    @PostMapping
    public ResponseEntity<AvaliacaoResponseDTO> criar(@RequestBody @Valid AvaliacaoRequestDTO dto) {
        return ResponseEntity.ok(service.criarAvaliacao(dto));
    }

    @GetMapping("/produto/{produtoId}")
    public ResponseEntity<List<AvaliacaoResponseDTO>> listarPorProduto(@PathVariable Long produtoId) {
        return ResponseEntity.ok(service.listarPorProduto(produtoId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvaliacaoResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AvaliacaoRequestDTO dto) {
        return ResponseEntity.ok(service.atualizarAvaliacao(id, dto));
    }

}