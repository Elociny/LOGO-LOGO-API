package com.logologo.api.service;

import com.logologo.api.dto.CarrinhoItemDTO;
import com.logologo.api.dto.CarrinhoAdicionarItemDTO;
import com.logologo.api.dto.CarrinhoResponseDTO;
import com.logologo.api.model.Carrinho;
import com.logologo.api.model.CarrinhoItem;
import com.logologo.api.model.Cliente;
import com.logologo.api.model.Produto;
import com.logologo.api.repository.CarrinhoItemRepository;
import com.logologo.api.repository.CarrinhoRepository;
import com.logologo.api.repository.ClienteRepository;
import com.logologo.api.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final CarrinhoItemRepository itemRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository,
                           CarrinhoItemRepository carrinhoItemRepository,
                           ClienteRepository clienteRepository,
                           ProdutoRepository produtoRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.itemRepository = carrinhoItemRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    public CarrinhoResponseDTO listarCarrinho(Long clienteId) {
        Carrinho carrinho = carrinhoRepository.findByClienteId(clienteId);

        if (carrinho == null) {
            throw new RuntimeException("Carrinho não encontrado");
        }

        List<CarrinhoItemDTO> itens = carrinho.getItens() == null ? List.of() : carrinho.getItens().stream()
                .map(item -> new CarrinhoItemDTO(
                        item.getId(),
                        item.getProduto().getId(),
                        item.getProduto().getNome(),
                        item.getQuantidade()
                )).toList();

        return new CarrinhoResponseDTO(carrinho.getId(), clienteId, itens);
    }

    public CarrinhoResponseDTO adicionarItem(Long clienteId, CarrinhoAdicionarItemDTO dto) {
        Carrinho carrinho = carrinhoRepository.findByClienteId(clienteId);

        if (carrinho == null) {
            throw new RuntimeException("Carrinho não encontrado");
        }

        Produto produto = produtoRepository.findById(dto.produtoId()).orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        CarrinhoItem item = new CarrinhoItem();
        item.setCarrinho(carrinho);
        item.setProduto(produto);
        item.setQuantidade(dto.quantidade());

        carrinho.getItens().add(item);
        itemRepository.save(item);

        return listarCarrinho(clienteId);
    }

    public CarrinhoResponseDTO removerItem(Long clienteId, Long itemId) {
        Carrinho carrinho = carrinhoRepository.findByClienteId(clienteId);

        if (carrinho == null) {
            throw new RuntimeException("Carrinho não encontrado");
        }

        CarrinhoItem item = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item não encontrado"));

        carrinho.getItens().remove(item);
        itemRepository.delete(item);

        return listarCarrinho(clienteId);
    }


    public CarrinhoResponseDTO atualizarQuantidade(Long clienteId, Long itemId, int quantidade) {
        Carrinho carrinho = getCarrinho(clienteId);

        CarrinhoItem carrinhoItem = carrinho.getItens().stream()
                .filter(i -> i.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        carrinhoItem.setQuantidade(quantidade);
        itemRepository.save(carrinhoItem);

        return toResponseDTO(carrinho);
    }


    private CarrinhoResponseDTO toResponseDTO(Carrinho carrinho) {
        List<CarrinhoItemDTO> itens = carrinho.getItens().stream()
                .map(i -> new CarrinhoItemDTO(
                        i.getId(),
                        i.getProduto().getId(),
                        i.getProduto().getNome(),
                        i.getQuantidade()
                )).toList();

        return new CarrinhoResponseDTO(carrinho.getId(), carrinho.getCliente().getId(), itens);
    }

    private Carrinho getCarrinho(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Carrinho carrinho = cliente.getCarrinho();
        if (carrinho == null) {
            carrinho = new Carrinho();
            carrinho.setCliente(cliente);
            carrinhoRepository.save(carrinho);
            cliente.setCarrinho(carrinho);
            clienteRepository.save(cliente);
        }

        return carrinho;
    }
}
