package com.logologo.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoDTO(
        Long id,

        @NotBlank(message = "Logradouro é obrigaório")
        String logradouro,

        @NotNull(message = "Número é obrigaório")
        Integer numero,

        String complemento,

        @NotBlank(message = "Bairro é obrigaório")
        String bairro,

        @NotBlank(message = "Cidade é obrigaório")
        String cidade,

        @NotBlank(message = "Estado é obrigaório")
        String estado,

        @NotBlank(message = "CEP é obrigaório")
        String cep,

        @NotNull(message = "Id do cliente é obrigatório")
        Long clienteId
) { }
