package com.logologo.api.dto;

import com.logologo.api.model.BandeiraCartao;
import com.logologo.api.model.TipoCartao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CartaoRequestDTO(
        @NotBlank(message = "Número do cartão é obrigatório")
        @Pattern(regexp = "^[0-9]{13,19}$", message = "Número do cartão deve conter entre 13 e 19 dígitos")
        String numero,

        @NotBlank(message = "Nome do titular é obrigatório")
        String nomeTitular,

        @NotBlank(message = "Validade é obrigatória")
        @Pattern(regexp = "^(0[1-9]|1[0-2])\\/([0-9]{2}|[0-9]{4})$", message = "Validade deve estar no formato MM/AA ou MM/AAAA")
        String validade,

        @NotBlank(message = "CVV é obrigatório")
        @Pattern(regexp = "^[0-9]{3,4}$", message = "CVV deve conter 3 ou 4 dígitos")
        String cvv,

        @NotNull(message = "Tipo do cartão é obrigatório")
        TipoCartao tipo,

        @NotNull(message = "Bandeira é obrigatória")
        BandeiraCartao bandeira,

        @NotNull(message = "ID do cliente é obrigatório")
        Long clienteId
) {}
