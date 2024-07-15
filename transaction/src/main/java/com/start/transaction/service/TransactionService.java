package com.start.transaction.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public TransactionService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "hello-topic", groupId = "group-1")
    public void receiveMessage(String message) {
        System.out.println("Consumer Message: " + message);
    }
}
