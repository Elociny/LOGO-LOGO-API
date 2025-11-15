package com.logologo.api.controller;

import com.logologo.api.dto.CarrinhoAdicionarItemDTO;
import com.logologo.api.dto.CarrinhoAtualizarQuantidadeDTO;
import com.logologo.api.dto.CarrinhoResponseDTO;
import com.logologo.api.service.CarrinhoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes/{clienteId}/carrinho")
@CrossOrigin(origins = "*")
public class CarrinhoController {

    private final CarrinhoService service;

    public CarrinhoController(CarrinhoService carrinhoService) {
        this.service = carrinhoService;
    }

    @GetMapping
    public ResponseEntity<CarrinhoResponseDTO> listar(@PathVariable Long clienteId) {
        return ResponseEntity.ok(service.listarCarrinho(clienteId));
    }

    @PostMapping("/itens")
    public ResponseEntity<CarrinhoResponseDTO> adicionar(@PathVariable Long clienteId, @RequestBody @Valid CarrinhoAdicionarItemDTO dto) {
        return ResponseEntity.ok(service.adicionarItem(clienteId, dto));
    }

    @PutMapping("/itens/{itemId}")
    public ResponseEntity<CarrinhoResponseDTO> atualizarQuantidade(
            @PathVariable Long clienteId,
            @PathVariable Long itemId,
            @RequestBody @Valid CarrinhoAtualizarQuantidadeDTO dto
    ) {
        return ResponseEntity.ok(service.atualizarQuantidade(clienteId, itemId, dto.quantidade()));
    }

    @DeleteMapping("/itens/{itemId}")
    public ResponseEntity<CarrinhoResponseDTO> remover (@PathVariable Long clienteId, @PathVariable Long itemId) {
        return ResponseEntity.ok(service.removerItem(clienteId, itemId));
    }
}
