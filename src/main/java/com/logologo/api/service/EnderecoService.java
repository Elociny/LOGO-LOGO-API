package com.logologo.api.service;

import com.logologo.api.dto.EnderecoDTO;
import com.logologo.api.dto.EnderecoDTO;
import com.logologo.api.model.Cliente;
import com.logologo.api.model.Endereco;
import com.logologo.api.repository.ClienteRepository;
import com.logologo.api.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ClienteRepository clienteRepository;

    public EnderecoService(EnderecoRepository enderecoRepository, ClienteRepository clienteRepository) {
        this.enderecoRepository = enderecoRepository;
        this.clienteRepository = clienteRepository;
    }

    public EnderecoDTO criarEndereco(EnderecoDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.clienteId()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Endereco endereco = Endereco.builder()
                .logradouro(dto.logradouro())
                .numero(dto.numero())
                .complemento(dto.complemento())
                .bairro(dto.bairro())
                .cidade(dto.cidade())
                .estado(dto.estado())
                .cep(dto.cep())
                .cliente(cliente)
                .build();

        return toResponseDTO(enderecoRepository.save(endereco));
    }

    public List<EnderecoDTO> listarTodos() {
        return enderecoRepository.findAll().stream().map(this::toResponseDTO).toList();
    }

    public List<EnderecoDTO> listarPorCliente(Long clienteId) {
        return enderecoRepository.findByClienteId(clienteId).stream().map(this::toResponseDTO).toList();
    }

    public EnderecoDTO atualizarEndereco(Long id, EnderecoDTO dto) {
        Endereco endereco = enderecoRepository.findById(id).orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        Cliente cliente = clienteRepository.findById(dto.clienteId()).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        endereco.setLogradouro(dto.logradouro());
        endereco.setNumero(dto.numero());
        endereco.setComplemento(dto.complemento());
        endereco.setBairro(dto.bairro());
        endereco.setCidade(dto.cidade());
        endereco.setEstado(dto.estado());
        endereco.setCep(dto.cep());

        return toResponseDTO(enderecoRepository.save(endereco));
    }

    public void excluirEndereco(Long id) {
        if (!enderecoRepository.existsById(id)) {
            throw new RuntimeException("Endereço não encontrado");
        }

        enderecoRepository.deleteById(id);
    }

    private EnderecoDTO toResponseDTO(Endereco e) {
        return new EnderecoDTO(
                e.getId(),
                e.getLogradouro(),
                e.getNumero(),
                e.getComplemento(),
                e.getBairro(),
                e.getCidade(),
                e.getEstado(),
                e.getCep(),
                e.getCliente().getId()
        );
    }
}
