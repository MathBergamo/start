package com.start.principal.service.kafka;

import com.start.principal.service.kafka.DTO.EmprestimoCadastroDTO;
import org.springframework.kafka.annotation.KafkaListener;

public class EmprestimoConsumer {

    @KafkaListener(topics = "emprestimo-topic", groupId = "grupo_cliente")
    public void consumirEmprestimo(EmprestimoCadastroDTO dto) {
    }
}
