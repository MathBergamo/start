package com.start.principal.Controller;

import com.start.principal.Model.DTO.ClientesDTO;
import com.start.principal.Service.ClientesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private ClientesService service;

    @PostMapping()
    public ResponseEntity<ClientesDTO> create(@RequestBody @Valid ClientesDTO dto) {
        return ResponseEntity.ok().body(service.create(dto));
    }
}
