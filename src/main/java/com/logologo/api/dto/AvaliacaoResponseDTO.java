package com.logologo.api.dto;

import java.time.LocalDateTime;

public record AvaliacaoResponseDTO(
        Long id,
        Long clienteId,
        String nomeCliente,
        String imagemCliente,
        Integer nota,
        String titulo,
        String descricao,
        LocalDateTime dataAvaliacao
) {}