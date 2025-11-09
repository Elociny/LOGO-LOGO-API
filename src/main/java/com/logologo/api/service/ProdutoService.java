package com.logologo.api.service;

import com.logologo.api.dto.ProdutoDTO;
import com.logologo.api.model.Produto;
import com.logologo.api.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<ProdutoDTO> listarTodos() {
        return repository.findAll().stream().map(this::toDTO).toList();
    }

    public List<ProdutoDTO> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome).stream().map(this::toDTO).toList();
    }

    private ProdutoDTO toDTO(Produto produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getDesconto(),
                produto.getPrecoComDesconto(),
                produto.getQuantidade(),
                produto.getCor(),
                produto.getTamanho(),
                produto.getCategoria(),
                produto.getImageUrl()
        );
    }
}
