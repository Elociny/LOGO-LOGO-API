package com.logologo.api.repository;

import com.logologo.api.model.Comprar;
import com.logologo.api.model.StatusCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComprarRepository extends JpaRepository<Comprar, Long> {

    List<Comprar> findByClienteId(Long clienteId);

    @Query("SELECT COUNT(c) > 0 FROM Comprar c JOIN c.produtos p " +
            "WHERE c.cliente.id = :clienteId " +
            "AND p.id = :produtoId " +
            "AND c.status = :status")
    boolean existeCompraEntregue(@Param("clienteId") Long clienteId,
                                 @Param("produtoId") Long produtoId,
                                 @Param("status") StatusCompra status);
}