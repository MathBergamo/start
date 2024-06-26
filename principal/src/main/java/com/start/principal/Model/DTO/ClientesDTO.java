package com.start.principal.Model.DTO;

import com.start.principal.Model.enums.ClientesCargo;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ClientesDTO {

    private ClientesCargo cargo;

    private String nome;

    private LocalDate dataNascimento;

    private BigDecimal salario;

    private String cpf;

}
