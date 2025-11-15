package com.logologo.api.dto;

public record CarrinhoItemDTO(
        Long id,
        Long produtoId,
        String nomeProduto,
        Integer quantidade
) {
}
