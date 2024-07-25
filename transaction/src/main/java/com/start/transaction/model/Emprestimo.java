package com.start.transaction.model;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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

    @Email(message = "O e-mail do cliente deve ser válido.")
    private String clienteEmail;

    @NotNull(message = "O valor do empréstimo não pode ser nulo.")
    @DecimalMin(value = "0.0", inclusive = false, message = "O valor do empréstimo deve ser maior que zero.")
    private BigDecimal valor;

    @NotNull(message = "A data de início não pode ser nula.")
    private LocalDate dataInicio;

    @NotNull(message = "A data de vencimento não pode ser nula.")
    private LocalDate dataVencimento;

    @AssertTrue(message = "A data de início não pode ser posterior à data de vencimento.")
    public boolean isDatesValid() {
        if (dataInicio == null || dataVencimento == null) {
            return true;
        }
        return !dataInicio.isAfter(dataVencimento);
    }

    public Emprestimo(String clienteEmail, BigDecimal valor, LocalDate dataInicio, LocalDate dataVencimento) {
        this.clienteEmail = clienteEmail;
        this.valor = valor;
        this.dataInicio = dataInicio;
        this.dataVencimento = dataVencimento;
    }
}
