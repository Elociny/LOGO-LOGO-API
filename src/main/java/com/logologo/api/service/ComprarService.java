package com.logologo.api.service;

import com.logologo.api.dto.ComprarRequestDTO;
import com.logologo.api.dto.ComprarResponseDTO;
import com.logologo.api.model.Cartao;
import com.logologo.api.model.Comprar;
import com.logologo.api.model.FormaPagamento;
import com.logologo.api.model.StatusCompra;
import com.logologo.api.repository.CartaoRepository;
import com.logologo.api.repository.ClienteRepository;
import com.logologo.api.repository.ComprarRepository;
import com.logologo.api.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ComprarService {

    private final ComprarRepository comprarRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final CartaoRepository cartaoRepository;

    public List<ComprarResponseDTO> listarTodos() {
        return comprarRepository.findAll().stream().map(this::toDTO).toList();
    }

    public ComprarResponseDTO salvar(ComprarRequestDTO dto) {
        var cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));


        var produtos = produtoRepository.findAllById(dto.produtosIds());
        if (produtos.isEmpty()) {
            throw new RuntimeException("Nenhum produto encontrado");
        }

        FormaPagamento formaPagamento = dto.formaPagamento();

        Cartao cartao = null;

        if (formaPagamento == FormaPagamento.CARTAO) {
            if (dto.cartaoId() == null) {
                throw new RuntimeException("Id do cartão é obrigatório quando a forma de pagamento é cartão");
            }

            cartao = cartaoRepository.findById(dto.cartaoId())
                    .orElseThrow(() -> new RuntimeException("Cartão não encontrado"));

            if (!cartao.getCliente().getId().equals(cliente.getId())) {
                throw new RuntimeException("O cartão não pretence ao cliente informado");
            }
        } else {
            if (dto.cartaoId() != null) {
                throw new RuntimeException("Id do cartão só pode ser enviado quando a forma de pagamento é cartão");
            }
        }

        Comprar compra = new Comprar();

        compra.setCliente(cliente);
        compra.setProdutos(produtos);
        compra.setDataCompra(LocalDateTime.now());
        compra.setStatus(StatusCompra.PENDENTE);
        compra.setFormaPagamento(formaPagamento);
        compra.setCartao(cartao);
        compra.calcularValorTotal();

        comprarRepository.save(compra);

        return toDTO(compra);
    }

    public ComprarResponseDTO buscarPorId(Long id) {
        return comprarRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada"));
    }

    public void deletar(Long id) {
        comprarRepository.deleteById(id);
    }

    private ComprarResponseDTO toDTO(Comprar comprar) {

        return new ComprarResponseDTO(
                comprar.getId(),
                comprar.getCliente().getId(),
                comprar.getProdutos().stream().map(p -> p.getId()).toList(),
                comprar.getDataCompra(),
                comprar.getValorTotal(),
                comprar.getStatus(),
                comprar.getFormaPagamento(),
                comprar.getCartao() != null ? comprar.getCartao().getId() : null
        );
    }

}
