package com.logologo.api.dto;

import com.logologo.api.model.TipoCartao;

public record CartaoResponseDTO(
        Long id,
        String numero,
        String nomeTitular,
        String validade,
        TipoCartao tipo,
        String bandeira,
        Long clienteId
) {}
