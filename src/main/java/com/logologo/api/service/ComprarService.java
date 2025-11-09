package com.logologo.api.service;

import com.logologo.api.model.Comprar;
import com.logologo.api.repository.ComprarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComprarService {

    private final ComprarRepository comprarRepository;

    public List<Comprar> listarTodos() {
        return comprarRepository.findAll();
    }

    public Comprar salvar(Comprar compra) {
        compra.calcularValorTotal();
        return comprarRepository.save(compra);
    }

    public Comprar buscarPorId(Long id) {
        return comprarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada"));
    }

    public void deletar(Long id) {
        comprarRepository.deleteById(id);
    }
}
