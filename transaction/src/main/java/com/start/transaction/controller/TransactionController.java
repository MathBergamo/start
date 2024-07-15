package com.start.transaction.controller;

import com.start.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/hello")
    public String hello(@PathVariable("name") String name) {
        transactionService.receiveMessage(name);
        return "OK";
    }
}
