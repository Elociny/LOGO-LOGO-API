package com.logologo.api.service;

import com.logologo.api.dto.AvaliacaoRequestDTO;
import com.logologo.api.dto.AvaliacaoResponseDTO;
import com.logologo.api.model.Avaliacao;
import com.logologo.api.model.Cliente;
import com.logologo.api.model.Produto;
import com.logologo.api.model.StatusCompra;
import com.logologo.api.repository.AvaliacaoRepository;
import com.logologo.api.repository.ClienteRepository;
import com.logologo.api.repository.ComprarRepository;
import com.logologo.api.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final ComprarRepository comprarRepository;

    @Transactional
    public AvaliacaoResponseDTO criarAvaliacao(AvaliacaoRequestDTO dto) {
        boolean podeAvaliar = comprarRepository.existeCompraEntregue(
                dto.clienteId(),
                dto.produtoId(),
                StatusCompra.ENTREGUE
        );

        if (!podeAvaliar) {
            throw new RuntimeException("Você só pode avaliar produtos que comprou e já foram entregues.");
        }

        if (avaliacaoRepository.existsByClienteIdAndProdutoId(dto.clienteId(), dto.produtoId())) {
            throw new RuntimeException("Você já avaliou este produto.");
        }

        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Produto produto = produtoRepository.findById(dto.produtoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Avaliacao avaliacao = Avaliacao.builder()
                .cliente(cliente)
                .produto(produto)
                .nota(dto.nota())
                .titulo(dto.titulo())
                .descricao(dto.descricao())
                .dataAvaliacao(LocalDateTime.now())
                .build();

        avaliacaoRepository.save(avaliacao);

        return toDTO(avaliacao);
    }

    public List<AvaliacaoResponseDTO> listarPorProduto(Long produtoId) {
        return avaliacaoRepository.findByProdutoId(produtoId).stream()
                .map(this::toDTO)
                .sorted((a, b) -> b.dataAvaliacao().compareTo(a.dataAvaliacao()))
                .toList();
    }

    @Transactional
    public AvaliacaoResponseDTO atualizarAvaliacao(Long id, AvaliacaoRequestDTO dto) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Avaliação não encontrada"));

        if (!avaliacao.getCliente().getId().equals(dto.clienteId())) {
            throw new RuntimeException("Você não tem permissão para editar esta avaliação.");
        }

        avaliacao.setNota(dto.nota());
        avaliacao.setTitulo(dto.titulo());
        avaliacao.setDescricao(dto.descricao());
        avaliacao.setDataAvaliacao(LocalDateTime.now());

        avaliacaoRepository.save(avaliacao);

        return toDTO(avaliacao);
    }

    private AvaliacaoResponseDTO toDTO(Avaliacao avaliacao) {
        return new AvaliacaoResponseDTO(
                avaliacao.getId(),
                avaliacao.getCliente().getId(),
                avaliacao.getCliente().getNome(),
                avaliacao.getCliente().getImageUrl(),
                avaliacao.getNota(),
                avaliacao.getTitulo(),
                avaliacao.getDescricao(),
                avaliacao.getDataAvaliacao()
        );
    }
}