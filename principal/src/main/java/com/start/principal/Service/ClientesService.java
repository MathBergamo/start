package com.start.principal.Service;

import com.start.principal.Model.Clientes;
import com.start.principal.Model.DTO.ClientesDTO;
import com.start.principal.Repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientesService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public ClientesDTO create(ClientesDTO dto) {
        Clientes novo_cliente = new Clientes(dto);
        repository.save(novo_cliente);
        return modelMapper.map(novo_cliente, ClientesDTO.class);
    }

    public ClientesDTO findById(Long id) {
        Optional<Clientes> clienteById = repository.findById(id);
        Clientes cliente = new Clientes();
        modelMapper.map(clienteById.get(), cliente);
        return modelMapper.map(cliente, ClientesDTO.class);
    }
}