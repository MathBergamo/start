package com.start.principal.service;

import com.start.principal.model.Clientes;
import com.start.principal.model.DTO.ClientesAtualizarDTO;
import com.start.principal.model.DTO.ClientesCadastroDTO;
import com.start.principal.model.DTO.ClientesFindDTO;
import com.start.principal.model.DTO.ClientesLoginDTO;
import com.start.principal.repository.ClientesRepository;
import com.start.principal.security.JwtService;
import com.start.principal.validation.ClientesValidation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientesService {

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClientesValidation clientesValidation;

    public List<ClientesFindDTO> findAll() {
        List<Clientes> clientes = clientesRepository.findAll();
        List<ClientesFindDTO> dtos = new ArrayList<>();

        for (Clientes cliente : clientes) {
            ClientesFindDTO dto = modelMapper.map(cliente, ClientesFindDTO.class);
            dtos.add(dto);
        }
        return dtos;
    }

    public Optional<ClientesFindDTO> findById(Long id) {
        Optional<Clientes> cliente = clientesRepository.findById(id);
        if (cliente.isPresent()) {
            ClientesFindDTO dto = modelMapper.map(cliente.get(), ClientesFindDTO.class);
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    public Optional<ClientesCadastroDTO> cadastrarCliente(ClientesCadastroDTO dto) {
        clientesValidation.senhaValidator(dto.getSenha());
        clientesValidation.nomeValidator(dto.getNome());
        clientesValidation.dataNascimentoValidator(dto.getDataNascimento());
        dto.setSenha(criptografarSenha(dto.getSenha()));

        Clientes clienteCadastrado = clientesRepository.save(modelMapper.map(dto, Clientes.class));

        return Optional.of(modelMapper.map(clienteCadastrado, ClientesCadastroDTO.class));
    }

    public Optional<ClientesLoginDTO> autenticarCliente(ClientesLoginDTO dto) {
        clientesValidation.autenticarClienteValidator(dto);

        return clientesRepository.findByEmail(dto.getEmail())
                .map(cliente -> {
                    dto.setId(cliente.getId());
                    dto.setNome(cliente.getNome());
                    dto.setEmail(cliente.getEmail());
                    dto.setToken(gerarToken(dto.getEmail()));
                    dto.setSenha("");
                    return dto;
                });
    }

    public Optional<ClientesAtualizarDTO> atualizarCliente(ClientesAtualizarDTO dto) {
        clientesValidation.atualizarClienteValidator(dto.getId());
        Optional<Clientes> buscaCliente = clientesRepository.findById(dto.getId());
        Clientes clienteAtualizado = buscaCliente.get();

        clientesValidation.emailValidator(dto.getEmail());
        clientesValidation.senhaValidator(dto.getSenha());
        dto.setSenha(criptografarSenha(dto.getSenha()));
        modelMapper.map(dto, clienteAtualizado);
        clientesRepository.save(clienteAtualizado);

        return Optional.of(dto);
    }

    public ResponseEntity<Void> delete(Long id) {
        clientesRepository.findById(id);
        try {
            clientesRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível realizar a exclusão!");
        }
        return ResponseEntity.noContent().build();
    }

    private String criptografarSenha(String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(senha);
    }

    private String gerarToken(String usuario) {
        return "Bearer " + jwtService.generateToken(usuario);
    }
}
