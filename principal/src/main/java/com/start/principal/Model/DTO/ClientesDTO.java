package com.start.principal.Model.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ClientesDTO {

    private String nome;

    private LocalDate dataNascimento;

    private BigDecimal salario;

    private String cpf;

    private String email;

    private String senha;
}
