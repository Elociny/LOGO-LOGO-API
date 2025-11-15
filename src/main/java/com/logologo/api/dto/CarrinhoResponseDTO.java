package com.logologo.api.dto;

import java.util.List;

public record CarrinhoResponseDTO(
        Long id,
        Long clienteId,
        List<CarrinhoItemDTO> itens
) {
}
