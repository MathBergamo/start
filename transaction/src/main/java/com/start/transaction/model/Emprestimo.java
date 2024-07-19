package com.start.transaction.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "emprestimo")
public class Emprestimo {

    @Id
    private String id;

    private String clienteEmail;

    private BigDecimal valor;

    private LocalDate dataInicio;

    private LocalDate dataVencimento;

}
