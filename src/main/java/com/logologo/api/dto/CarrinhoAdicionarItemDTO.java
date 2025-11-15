package com.logologo.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CarrinhoAdicionarItemDTO(
        @NotNull
        Long produtoId,

        @NotNull
        @Min(1)
        Integer quantidade
) {
}
