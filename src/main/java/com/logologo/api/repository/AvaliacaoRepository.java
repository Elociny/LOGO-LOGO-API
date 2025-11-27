package com.logologo.api.repository;

import com.logologo.api.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByProdutoId(Long produtoId);

    boolean existsByClienteIdAndProdutoId(Long clienteId, Long produtoId);
}