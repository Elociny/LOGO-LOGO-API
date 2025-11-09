package com.logologo.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 1000)
    private String descricao;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    private int desconto;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private String cor;

    @Column(nullable = false)
    private String tamanho;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;

    @Column(nullable = false)
    private String imageUrl;

    public BigDecimal getPrecoComDesconto() {
    if (preco == null) {
        return BigDecimal.ZERO;
    }

    if (desconto > 0) {
        BigDecimal descontoDecimal = BigDecimal.valueOf(desconto)
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        BigDecimal precoComDesconto = preco.subtract(preco.multiply(descontoDecimal));

        return precoComDesconto.setScale(2, RoundingMode.HALF_UP);
    }

    return preco;

    }
}
