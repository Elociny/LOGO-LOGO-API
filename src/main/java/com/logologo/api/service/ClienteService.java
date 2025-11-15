package com.logologo.api.service;

import com.logologo.api.dto.*;
import com.logologo.api.model.Cartao;
import com.logologo.api.model.Cliente;
import com.logologo.api.repository.ClienteRepository;
import com.logologo.api.utils.CartaoUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<ClienteResponseDTO> listarTodos() {
        return repository.findAll().stream().map(this::toResponseDTO).toList();
    }

    public ClienteResponseDTO buscarPorId(Long id) {
        Cliente cliente = repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        return toResponseDTO(cliente);
    }

    public ClienteResponseDTO cadastrar(ClienteRequestDTO dto) {
        if (repository.existsByEmail(dto.email())) {
            throw new RuntimeException("Email já cadastrado");
        }

        Cliente cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setEmail(dto.email());
        cliente.setSenha(dto.senha());

        repository.save(cliente);
        return toResponseDTO(cliente);
    }

    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto) {
        Cliente cliente = repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        if (!cliente.getEmail().equals(dto.email())) {
            throw new RuntimeException("Email não pode ser alterado");
        }

        cliente.setNome(dto.nome());
        cliente.setSenha(dto.senha());

        repository.save(cliente);
        return toResponseDTO(cliente);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    private ClienteResponseDTO toResponseDTO(Cliente cliente) {
        List<EnderecoDTO> enderecos = cliente.getEnderecos() == null ? List.of() : cliente.getEnderecos().stream()
                .map(e -> new EnderecoDTO(
                        e.getId(),
                        e.getLogradouro(),
                        e.getNumero(),
                        e.getComplemento(),
                        e.getBairro(),
                        e.getCidade(),
                        e.getEstado(),
                        e.getCep(),
                        e.getCliente().getId()
                )).toList();

        List<CartaoResponseDTO> cartoes = cliente.getCartoes() == null ? List.of() :
                cliente.getCartoes().stream()
                        .map(c -> new CartaoResponseDTO(
                                c.getId(),
                                CartaoUtils.mascararNumero(c.getNumero()),
                                c.getNomeTitular(),
                                c.getValidade(),
                                c.getTipo(),
                                c.getBandeira().name(),
                                cliente.getId()
                        )).toList();

        List<ComprarResponseDTO> compras = cliente.getCompras() == null ? List.of() :
                cliente.getCompras().stream()
                        .map(comprar -> new ComprarResponseDTO(
                                comprar.getId(),
                                cliente.getId(),
                                comprar.getProdutos().stream().map(produto -> produto.getId()).toList(),
                                comprar.getDataCompra(),
                                comprar.getValorTotal(),
                                comprar.getStatus(),
                                comprar.getFormaPagamento(),
                                comprar.getCartao() != null ? comprar.getCartao().getId() : null
                        )).toList();

        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                enderecos,
                cartoes,
                compras
        );
    }
}
