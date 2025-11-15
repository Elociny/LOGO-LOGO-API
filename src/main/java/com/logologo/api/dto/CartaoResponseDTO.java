package com.logologo.api.dto;

import com.logologo.api.model.BandeiraCartao;
import com.logologo.api.model.TipoCartao;

public record CartaoResponseDTO(
        Long id,
        String numeroMascarado,
        String nomeTitular,
        String validade,
        TipoCartao tipo,
        String bandeira,
        Long clienteId
) {}

