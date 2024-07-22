package com.start.principal.service;


import com.start.principal.service.kafka.DTO.EmprestimoCadastroDTO;
import com.start.principal.service.kafka.EmprestimoProducer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoProducer emprestimoProducer;

    @Transactional
    public void cadastrarEmprestimo(EmprestimoCadastroDTO dto) {
        emprestimoProducer.enviarEmprestimo(dto);
    }
}
