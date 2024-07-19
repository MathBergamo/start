package com.start.transaction.service.kafka.DTO;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmprestimoCadastroDTO implements Serializable {

    private String id;
    private String clienteEmail;
    private BigDecimal valor;
    private LocalDate dataInicio;
    private LocalDate dataVencimento;

}
