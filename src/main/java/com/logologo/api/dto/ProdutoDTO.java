package com.logologo.api.dto;

import com.logologo.api.model.Categoria;
import java.math.BigDecimal;

public record ProdutoDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        int desconto,
        BigDecimal precoComDesconto,
        int quantidade,
        String cor,
        String tamanho,
        Categoria categoria,
        String imageUrl
) { }
