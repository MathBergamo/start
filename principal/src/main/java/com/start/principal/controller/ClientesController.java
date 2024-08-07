package com.start.principal.controller;

import com.start.principal.model.DTO.ClientesAtualizarDTO;
import com.start.principal.model.DTO.ClientesCadastroDTO;
import com.start.principal.model.DTO.ClientesFindDTO;
import com.start.principal.model.DTO.ClientesLoginDTO;
import com.start.principal.repository.ClientesRepository;
import com.start.principal.service.ClientesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private ClientesService clientesService;

    @Autowired
    private ClientesRepository clientesRepository;

    @GetMapping("/all")
    public ResponseEntity<List<ClientesFindDTO>> findAll() {
        return ResponseEntity.ok(clientesService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientesFindDTO> findById(@PathVariable Long id) {
        return clientesService.findById(id)
                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/login")
    public ResponseEntity<ClientesLoginDTO> autenticarCliente(@RequestBody @Valid ClientesLoginDTO dto) {
        return clientesService.autenticarCliente(dto)
                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ClientesCadastroDTO> cadastrarCliente(@RequestBody @Valid ClientesCadastroDTO dto) {
        return clientesService.cadastrarCliente(dto)
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping("/atualizar")
    public ResponseEntity<ClientesAtualizarDTO> atualizarCliente(@RequestBody @Valid ClientesAtualizarDTO dto) {
        return clientesService.atualizarCliente(dto)
                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return clientesService.delete(id);
    }
}
