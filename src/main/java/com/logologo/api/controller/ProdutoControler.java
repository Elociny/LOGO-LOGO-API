package com.logologo.api.controller;

import com.logologo.api.dto.ProdutoDTO;
import com.logologo.api.model.Produto;
import com.logologo.api.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*")
public class ProdutoControler {

    public final ProdutoService service;

    public ProdutoControler(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProdutoDTO> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/buscar")
    public List<ProdutoDTO> buscarPorNome(@RequestParam String nome) {
        return service.buscarPorNome(nome);
    }
}
