package com.logologo.api.dto;

import java.math.BigDecimal;

public record CarrinhoItemDTO(
        Long id,
        Long produtoId,
        String nomeProduto,
        String imageUrl,
        BigDecimal preco,
        String cor,
        String tamanho,
        Integer quantidade
) {
}