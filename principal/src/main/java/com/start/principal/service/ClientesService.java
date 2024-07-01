package com.start.principal.service;

import com.start.principal.model.Clientes;
import com.start.principal.model.DTO.ClientesAtualizarDTO;
import com.start.principal.model.DTO.ClientesCadastroDTO;
import com.start.principal.model.DTO.ClientesLoginDTO;
import com.start.principal.repository.ClientesRepository;
import com.start.principal.security.JwtService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ClientesService {


    @Autowired
    private ClientesRepository clientesRepository;


    @Autowired
    private JwtService jwtService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ModelMapper modelMapper;


    public Optional<ClientesCadastroDTO> cadastrarCliente(ClientesCadastroDTO dto) {
        if (clientesRepository.findByEmail(dto.getEmail()).isPresent()) {
            return Optional.empty();
        }
        dto.setSenha(criptografarSenha(dto.getSenha()));
        Clientes clienteCadastrado = clientesRepository.save(modelMapper.map(dto, Clientes.class));
        return Optional.of(modelMapper.map(clienteCadastrado, ClientesCadastroDTO.class));
    }

    public Optional<ClientesLoginDTO> autenticarCliente(ClientesLoginDTO dto) {
        var credenciais = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha());
        Authentication authentication = authenticationManager.authenticate(credenciais);

        if (authentication.isAuthenticated()) {
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

        return Optional.empty();
    }


    public Optional<ClientesAtualizarDTO> atualizarCliente(ClientesAtualizarDTO dto) {
        if (clientesRepository.findById(dto.getId()).isPresent()) {
            Optional<Clientes> buscaUsuario = clientesRepository.findById(dto.getId());

            if ((buscaUsuario.isPresent()) && (!buscaUsuario.get().getId().equals(dto.getId()))) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já está em uso!", null);
            }
            Clientes clienteAtualizado = buscaUsuario.get();
            modelMapper.map(dto, clienteAtualizado);
            clientesRepository.save(clienteAtualizado);
            return Optional.of(dto);
        }

        return Optional.empty();
    }

    private String criptografarSenha(String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(senha);
    }

    private String gerarToken(String usuario) {
        return "Bearer " + jwtService.generateToken(usuario);
    }
}
