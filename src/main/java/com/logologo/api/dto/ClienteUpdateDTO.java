package com.logologo.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClienteUpdateDTO(
        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 2, max = 100)
        String nome,

        @NotBlank(message = "O email é obrigatório")
        @Email
        String email,

        String telefone,

        String imageUrl
) {}