package com.logologo.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ClienteResponseDTO(

        @NotNull(message = "ID do cliente é obrigatório")
        Long id,

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Email deve ser válido")
        String email,

        List<EnderecoDTO> endercos,

        List<CartaoResponseDTO> cartoes,

        List<ComprarResponseDTO> compras,

        CarrinhoResponseDTO carrinho
) {}
