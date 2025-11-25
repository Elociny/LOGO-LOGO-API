package com.logologo.api.dto;

import java.math.BigDecimal;

public record ItemCompraDTO(
        Long id,
        String nome,
        String imageUrl,
        String cor,
        String tamanho,
        BigDecimal preco
) {}