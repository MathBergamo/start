package com.start.principal.service.kafka.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmprestimoCadastroDTO implements Serializable {

    private String id;

    @Email(message = "O e-mail do cliente deve ser válido.")
    @JsonProperty(required = true)
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

}
