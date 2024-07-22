package com.start.transaction.service.kafka;

import com.start.transaction.service.EmprestimoService;
import com.start.transaction.service.kafka.DTO.EmprestimoCadastroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmprestimoConsumer {

    @Autowired
    private EmprestimoService emprestimoService;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public EmprestimoConsumer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "emprestimoCadastroPrincipal-topic", groupId = "grupo_emprestimoCadastroPrincipal")
    public void consumirEmprestimo(EmprestimoCadastroDTO dto) {
        emprestimoService.cadastrarEmprestimo(dto);
    }
}
