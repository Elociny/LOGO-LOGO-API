package com.logologo.api.controller;

import com.logologo.api.dto.EnderecoDTO;
import com.logologo.api.dto.EnderecoDTO;
import com.logologo.api.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService service;

    public EnderecoController(EnderecoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> criar(@RequestBody @Valid EnderecoDTO dto) {
        return ResponseEntity.ok(service.criarEndereco(dto));
    }

    @GetMapping
    public List<EnderecoDTO> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<EnderecoDTO>> listarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(service.listarPorCliente(clienteId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid EnderecoDTO dto) {
        return ResponseEntity.ok(service.atualizarEndereco(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluirEndereco(id);

        return ResponseEntity.noContent().build();
    }
}
