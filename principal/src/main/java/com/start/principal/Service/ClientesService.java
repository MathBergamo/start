package com.start.principal.Service;

import com.start.principal.Model.Clientes;
import com.start.principal.Model.DTO.ClientesDTO;
import com.start.principal.Repository.ClienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public ClientesDTO create(ClientesDTO dto) {
        Clientes novo_cliente = new Clientes(dto);
        repository.save(novo_cliente);
        return modelMapper.map(novo_cliente, ClientesDTO.class);
    }
}
