package com.logologo.api.service;

import com.logologo.api.dto.ComprarRequestDTO;
import com.logologo.api.dto.ComprarResponseDTO;
import com.logologo.api.dto.ItemCompraDTO;
import com.logologo.api.model.*;
import com.logologo.api.repository.CartaoRepository;
import com.logologo.api.repository.ClienteRepository;
import com.logologo.api.repository.ComprarRepository;
import com.logologo.api.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Transactional
    public ComprarResponseDTO salvar(ComprarRequestDTO dto) {
        var cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        var produtosUnicos = produtoRepository.findAllById(dto.produtosIds());
        if (produtosUnicos.isEmpty()) {
            throw new RuntimeException("Nenhum produto encontrado");
        }

        List<Produto> produtosDaCompra = new ArrayList<>();
        for (Long idSolicitado : dto.produtosIds()) {
            produtosUnicos.stream()
                    .filter(p -> p.getId().equals(idSolicitado))
                    .findFirst()
                    .ifPresent(produtosDaCompra::add);
        }

        FormaPagamento formaPagamento = dto.formaPagamento();
        Cartao cartao = null;

        if (formaPagamento == FormaPagamento.CARTAO) {
            if (dto.cartaoId() == null) {
                throw new RuntimeException("Id do cartão é obrigatório quando a forma de pagamento é cartão");
            }

            cartao = cartaoRepository.findById(dto.cartaoId())
                    .orElseThrow(() -> new RuntimeException("Cartão não encontrado"));
        }

        Comprar compra = new Comprar();
        compra.setCliente(cliente);
        compra.setProdutos(produtosDaCompra);
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

    // Adicione este método
    public List<ComprarResponseDTO> listarPorCliente(Long clienteId) {
        return comprarRepository.findByClienteId(clienteId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    private ComprarResponseDTO toDTO(Comprar comprar) {
        List<ItemCompraDTO> itensDTO = comprar.getProdutos().stream()
                .map(p -> new ItemCompraDTO(
                        p.getId(),
                        p.getNome(),
                        p.getImageUrl(),
                        p.getCor(),
                        p.getTamanho(),
                        p.getPrecoComDesconto()
                )).toList();

        return new ComprarResponseDTO(
                comprar.getId(),
                comprar.getCliente().getId(),
                itensDTO,
                comprar.getDataCompra(),
                comprar.getValorTotal(),
                comprar.getStatus(),
                comprar.getFormaPagamento(),
                comprar.getCartao() != null ? comprar.getCartao().getId() : null
        );
    }

}
