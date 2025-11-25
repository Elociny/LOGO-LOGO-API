package com.logologo.api.dto;

import com.logologo.api.model.FormaPagamento;
import com.logologo.api.model.StatusCompra;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ComprarResponseDTO(
        Long id,
        Long clienteId,
        List<ItemCompraDTO> itens,
        LocalDateTime dataCompra,
        BigDecimal valorTotal,
        StatusCompra status,
        FormaPagamento formaPagamento,
        Long cartaoId
) {
}