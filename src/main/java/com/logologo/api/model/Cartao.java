package com.logologo.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cartoes")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Número do cartão é obrigatório")
    private String numero;

    @NotBlank(message = "Nome do titular é obrigatório")
    private String nomeTitular;

    @NotBlank(message = "Validade é obrigatória")
    private String validade;

    @NotBlank(message = "CVV é obrigatório")
    @JsonIgnore
    private String cvv;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCartao tipo; 

    @Column(nullable = false)
    private BandeiraCartao bandeira;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonBackReference
    private Cliente cliente;
}
