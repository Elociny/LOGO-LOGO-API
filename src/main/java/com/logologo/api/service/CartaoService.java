package com.logologo.api.service;

import com.logologo.api.dto.CartaoRequestDTO;
import com.logologo.api.dto.CartaoResponseDTO;
import com.logologo.api.model.Cartao;
import com.logologo.api.model.Cliente;
import com.logologo.api.repository.CartaoRepository;
import com.logologo.api.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaoService {

    private final CartaoRepository cartaoRepository;
    private final ClienteRepository clienteRepository;

    public CartaoService(CartaoRepository cartaoRepository, ClienteRepository clienteRepository) {
        this.cartaoRepository = cartaoRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<CartaoResponseDTO> listarTodos() {
        return cartaoRepository.findAll().stream().map(this::toResponseDTO).toList();
    }

    public CartaoResponseDTO buscarPorId(Long id) {
        Cartao cartao = cartaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cartão não encontrado"));
        return toResponseDTO(cartao);
    }

    public CartaoResponseDTO cadastrar(CartaoRequestDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Cartao cartao = new Cartao();
        cartao.setNumero(dto.numero());
        cartao.setNomeTitular(dto.nomeTitular());
        cartao.setValidade(dto.validade());
        cartao.setCvv(dto.cvv());
        cartao.setTipo(dto.tipo());
        cartao.setBandeira(dto.bandeira());
        cartao.setCliente(cliente);

        cartaoRepository.save(cartao);
        return toResponseDTO(cartao);
    }

    public CartaoResponseDTO atualizar(Long id, CartaoRequestDTO dto) {
        Cartao cartao = cartaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cartão não encontrado"));

        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cartao.setNumero(dto.numero());
        cartao.setNomeTitular(dto.nomeTitular());
        cartao.setValidade(dto.validade());
        cartao.setCvv(dto.cvv());
        cartao.setTipo(dto.tipo());
        cartao.setBandeira(dto.bandeira());
        cartao.setCliente(cliente);

        cartaoRepository.save(cartao);
        return toResponseDTO(cartao);
    }

    public void excluir(Long id) {
        cartaoRepository.deleteById(id);
    }

    private CartaoResponseDTO toResponseDTO(Cartao cartao) {
        return new CartaoResponseDTO(
                cartao.getId(),
                cartao.getNumero(),
                cartao.getNomeTitular(),
                cartao.getValidade(),
                cartao.getTipo(),
                cartao.getBandeira(),
                cartao.getCliente().getId()
        );
    }
}
