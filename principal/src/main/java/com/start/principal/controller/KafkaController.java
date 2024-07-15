package com.start.principal.controller;

import com.start.principal.service.kafka.TransactionalProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    private TransactionalProducer transactionalProducer;

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        transactionalProducer.sendMessage("Olá, " + name);
        return "OK";
    }
}
