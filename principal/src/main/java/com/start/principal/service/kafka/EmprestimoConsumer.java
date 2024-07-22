package com.start.principal.service.kafka;

import com.start.principal.service.EmprestimoService;
import com.start.principal.service.kafka.DTO.EmprestimoCadastroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EmprestimoConsumer {

    @Autowired
    private EmprestimoService emprestimoService;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public EmprestimoConsumer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private final Map<String, CompletableFuture<EmprestimoCadastroDTO>> pendingRequests = new ConcurrentHashMap<>();

    public void addPendingRequest(String id, CompletableFuture<EmprestimoCadastroDTO> future) {
        pendingRequests.put(id, future);
    }

    @KafkaListener(topics = "emprestimoCadastroTransaction-topic", groupId = "grupo_emprestimoCadastroTransaction")
    public void consumirEmprestimo(EmprestimoCadastroDTO dto) {
        CompletableFuture<EmprestimoCadastroDTO> future = pendingRequests.remove(dto.getId());
        if (future != null) {
            future.complete(dto);
        }
    }
}
