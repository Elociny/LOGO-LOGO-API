package com.logologo.api.repository;

import com.logologo.api.model.Comprar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComprarRepository extends JpaRepository<Comprar, Long> {
    List<Comprar> findByClienteId(Long clienteId);
}
