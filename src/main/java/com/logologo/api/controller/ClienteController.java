package com.logologo.api.controller;

import com.logologo.api.dto.*;
import com.logologo.api.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteResponseDTO> cadastrar(@RequestBody @Valid ClienteRequestDTO dto) {
        return ResponseEntity.ok(service.cadastrar(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<ClienteResponseDTO> login(@RequestBody LoginDTO dados) {
        ClienteResponseDTO cliente = service.login(dados.email(), dados.senha());

        return ResponseEntity.ok(cliente);
    }

    @PutMapping(value = "/{id}", consumes = { "multipart/form-data" })
    public ResponseEntity<ClienteResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestParam("nome") String nome,
            @RequestParam("email") String email,
            @RequestParam("telefone") String telefone,
            @RequestPart(value = "imagem", required = false) MultipartFile imagem
    ) {
        ClienteUpdateDTO dto = new ClienteUpdateDTO(nome, email, telefone, null);

        return ResponseEntity.ok(service.atualizar(id, dto, imagem));
    }

    @PutMapping("/alterar-senha")
    public ResponseEntity<String> alterarSenha(@RequestBody @Valid AlterarSenhaClienteDTO dados) {
        try {
            service.alteraSenha(dados.email(), dados.novaSenha());
            return ResponseEntity.ok("Senha alterada com sucesso");

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
