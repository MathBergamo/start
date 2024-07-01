package com.start.principal.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ClientesCadastroDTO {

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
    private String nome;
    private LocalDate dataNascimento;
    private BigDecimal salario;
    private String cpf;

}
