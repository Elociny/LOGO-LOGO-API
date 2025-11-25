package com.logologo.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Logradouro é obrigatório")
    private String logradouro;

    @NotNull(message = "Número é obrigatório")
    private Integer numero;

    @NotBlank(message = "Complemento é obrigatório")
    private String complemento;

    @NotBlank(message = "Bairro é obrigatório")
    private String bairro;

    @NotBlank(message = "Cidade é obrigatório")
    private String cidade;

    @NotBlank(message = "Estado é obrigatório")
    private String estado;

    @NotBlank(message = "CEP é obrigatório")
    private String cep;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
