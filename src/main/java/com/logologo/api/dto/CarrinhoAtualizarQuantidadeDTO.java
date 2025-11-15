package com.logologo.api.dto;

import jakarta.validation.constraints.NotNull;

public record CarrinhoAtualizarQuantidadeDTO(
        @NotNull
        int quantidade
) {
}
