package com.start.principal.Controller;

import com.start.principal.Model.DTO.ClientesDTO;
import com.start.principal.Service.ClientesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private ClientesService service;

    @PostMapping()
    public ResponseEntity<ClientesDTO> create(@RequestBody @Valid ClientesDTO dto) {
        return ResponseEntity.ok().body(service.create(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClientesDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientesDTO> findById(@PathVariable @Valid Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PatchMapping
    public ResponseEntity<ClientesDTO> update(@Valid @RequestBody ClientesDTO dto) {
        return ResponseEntity.ok(service.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
