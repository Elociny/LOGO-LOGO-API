package com.logologo.api.repository;

import com.logologo.api.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    List<Cartao> findByClienteId(Long clienteId);

    Optional<Cartao> findByNumeroAndClienteId(String numero, Long clienteId);
}
