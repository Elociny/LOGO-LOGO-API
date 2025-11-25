package com.logologo.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AlterarSenhaClienteDTO(
        @NotBlank
        String email,
        @NotBlank
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String novaSenha
) { }
