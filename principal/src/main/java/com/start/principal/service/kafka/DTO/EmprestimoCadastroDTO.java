package com.start.principal.service.kafka.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmprestimoCadastroDTO implements Serializable {

    private String id;

    @Email(message = "O formato do email é inválido.")
    private String clienteEmail;
    private BigDecimal valor;
    private LocalDate dataInicio;
    private LocalDate dataVencimento;

}
