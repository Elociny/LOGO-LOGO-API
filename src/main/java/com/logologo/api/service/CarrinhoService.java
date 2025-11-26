package com.logologo.api.service;

import com.logologo.api.dto.*;
import com.logologo.api.model.*;
import com.logologo.api.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final CarrinhoItemRepository itemRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository,
                           CarrinhoItemRepository itemRepository,
                           ClienteRepository clienteRepository,
                           ProdutoRepository produtoRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.itemRepository = itemRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    private Carrinho getCarrinhoOuCria(Long clienteId) {
        Carrinho carrinho = carrinhoRepository.findByClienteId(clienteId);

        if (carrinho == null) {
            Cliente cliente = clienteRepository.findById(clienteId)
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

            carrinho = new Carrinho();
            carrinho.setCliente(cliente);
            carrinho.setItens(new ArrayList<>());
            carrinhoRepository.save(carrinho);
        }
        return carrinho;
    }

    public CarrinhoResponseDTO listarCarrinho(Long clienteId) {
        Carrinho carrinho = getCarrinhoOuCria(clienteId);
        return toResponseDTO(carrinho);
    }

    @Transactional
    public CarrinhoResponseDTO adicionarItem(Long clienteId, CarrinhoAdicionarItemDTO dto) {
        Carrinho carrinho = getCarrinhoOuCria(clienteId);

        Produto produto = produtoRepository.findById(dto.produtoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Optional<CarrinhoItem> itemExistente = carrinho.getItens().stream()
                .filter(item -> item.getProduto().getId().equals(produto.getId()))
                .findFirst();

        if (itemExistente.isPresent()) {
            CarrinhoItem item = itemExistente.get();
            int novaQuantidade = item.getQuantidade() + dto.quantidade();

            if (novaQuantidade > produto.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente. Disponível: " + produto.getQuantidade());
            }

            item.setQuantidade(novaQuantidade);
            itemRepository.save(item);
        } else {
            if (dto.quantidade() > produto.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente. Disponível: " + produto.getQuantidade());
            }

            CarrinhoItem novoItem = new CarrinhoItem();
            novoItem.setCarrinho(carrinho);
            novoItem.setProduto(produto);
            novoItem.setQuantidade(dto.quantidade());
            novoItem.setPrecoNoMomento(produto.getPreco().doubleValue());

            carrinho.getItens().add(novoItem);
            itemRepository.save(novoItem);
        }

        return toResponseDTO(carrinho);
    }

    @Transactional
    public CarrinhoResponseDTO removerItem(Long clienteId, Long itemId) {
        Carrinho carrinho = getCarrinhoOuCria(clienteId);

        CarrinhoItem item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        carrinho.getItens().remove(item);
        itemRepository.delete(item);

        return toResponseDTO(carrinho);
    }

    @Transactional
    public CarrinhoResponseDTO atualizarQuantidade(Long clienteId, Long itemId, int quantidade) {
        Carrinho carrinho = getCarrinhoOuCria(clienteId);

        CarrinhoItem item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        if (quantidade > item.getProduto().getQuantidade()) {
            throw new RuntimeException("Estoque insuficiente. Máximo disponível: " + item.getProduto().getQuantidade());
        }

        item.setQuantidade(quantidade);
        itemRepository.save(item);

        return toResponseDTO(carrinho);
    }

    @Transactional
    public void limparCarrinho(Long clienteId) {
        Carrinho carrinho = carrinhoRepository.findByClienteId(clienteId);

        if (carrinho != null) {
            carrinho.getItens().clear();
            carrinhoRepository.save(carrinho);
        }
    }

    private CarrinhoResponseDTO toResponseDTO(Carrinho carrinho) {
        List<CarrinhoItemDTO> itensDTO = carrinho.getItens().stream()
                .map(item -> new CarrinhoItemDTO(
                        item.getId(),
                        item.getProduto().getId(),
                        item.getProduto().getNome(),
                        item.getProduto().getImageUrl(),
                        item.getProduto().getPreco(),
                        item.getProduto().getCor(),
                        item.getProduto().getTamanho(),
                        item.getQuantidade(),
                        item.getProduto().getQuantidade()
                )).toList();

        return new CarrinhoResponseDTO(carrinho.getId(), carrinho.getCliente().getId(), itensDTO);
    }
}