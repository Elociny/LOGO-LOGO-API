package com.logologo.api.repository;

import com.logologo.api.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
    Carrinho findByClienteId(Long clienteId);
}
