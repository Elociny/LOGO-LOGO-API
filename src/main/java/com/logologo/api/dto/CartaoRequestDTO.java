package com.logologo.api.dto;

import com.logologo.api.model.TipoCartao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CartaoRequestDTO(
        @NotBlank(message = "Número do cartão é obrigatório")
        String numero,

        @NotBlank(message = "Nome do titular é obrigatório")
        String nomeTitular,

        @NotBlank(message = "Validade é obrigatória")
        String validade,

        @NotBlank(message = "CVV é obrigatório")
        String cvv,

        @NotNull(message = "Tipo do cartão é obrigatório")
        TipoCartao tipo,

        @NotBlank(message = "Bandeira é obrigatória")
        String bandeira,

        @NotNull(message = "ID do cliente é obrigatório")
        Long clienteId
) {}
