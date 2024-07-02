package com.start.principal.model.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ClientesFindDTO {

    private Long id;
    private String email;
    private String nome;
    private LocalDate dataNascimento;
    private BigDecimal salario;
    private String cpf;
}
