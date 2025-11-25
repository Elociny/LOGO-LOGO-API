package com.logologo.api.repository;

import com.logologo.api.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByEmail(String email);

    Optional<Cliente> findByEmailAndSenha(String email, String senha);

    Optional<Cliente> findByEmail(String email);
}
