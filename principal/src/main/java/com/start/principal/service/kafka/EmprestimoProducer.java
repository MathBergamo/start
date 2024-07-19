package com.start.principal.service.kafka;

import com.start.principal.service.kafka.DTO.EmprestimoCadastroDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmprestimoProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public EmprestimoProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarEmprestimo(EmprestimoCadastroDTO dto) {
        kafkaTemplate.send("emprestimo-topic", dto);
    }

}
