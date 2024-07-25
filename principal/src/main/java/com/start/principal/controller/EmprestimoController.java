package com.start.principal.controller;

import com.start.principal.service.EmprestimoService;
import com.start.principal.service.kafka.DTO.EmprestimoCadastroDTO;
import com.start.principal.service.kafka.EmprestimoConsumer;
import com.start.principal.service.kafka.EmprestimoProducer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoProducer emprestimoProducer;

    @Autowired
    private EmprestimoService emprestimoService;

    @Autowired
    private EmprestimoConsumer emprestimoConsumer;

    @PostMapping("/cadastrar")
    public ResponseEntity<EmprestimoCadastroDTO> cadastrarCliente(@RequestBody @Valid EmprestimoCadastroDTO dto) {
        CompletableFuture<EmprestimoCadastroDTO> future = new CompletableFuture<>();
        dto.setId(UUID.randomUUID().toString());

        emprestimoConsumer.addPendingRequest(dto.getId(), future);
        emprestimoService.cadastrarEmprestimo(dto);

        try {
            EmprestimoCadastroDTO resposta = future.get(10, TimeUnit.SECONDS);
            return ResponseEntity.status(HttpStatus.CREATED).body(resposta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
