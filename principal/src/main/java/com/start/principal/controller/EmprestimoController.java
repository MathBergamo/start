package com.start.principal.controller;

import com.start.principal.service.kafka.DTO.EmprestimoCadastroDTO;
import com.start.principal.service.kafka.EmprestimoProducer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoProducer emprestimoProducer;

    @PostMapping("/cadastrar")
    public void cadastrarCliente(@RequestBody @Valid EmprestimoCadastroDTO dto) {
        emprestimoProducer.enviarEmprestimo(dto);
    }
}
