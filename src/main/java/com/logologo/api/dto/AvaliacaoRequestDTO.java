package com.logologo.api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AvaliacaoRequestDTO(
        @NotNull Long clienteId,
        @NotNull Long produtoId,

        @NotNull
        @Min(1) @Max(5)
        Integer nota,

        @NotBlank String titulo,
        String descricao
) {}