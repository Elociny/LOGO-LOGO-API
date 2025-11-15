package com.logologo.api.dto;

import com.logologo.api.model.FormaPagamento;

import java.util.List;

public record ComprarRequestDTO(
        Long clienteId,
        List<Long> produtosIds,
        FormaPagamento formaPagamento,
        Long cartaoId
) {
}
