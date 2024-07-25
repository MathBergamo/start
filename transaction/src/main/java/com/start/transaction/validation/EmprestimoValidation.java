package com.start.transaction.validation;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmprestimoValidation {

    public void dataValidator(LocalDate dataInicio, LocalDate dataVencimento) {
        LocalDate currentDate = LocalDate.now();

        try {
            if (dataInicio.isAfter(dataVencimento) || dataVencimento.isBefore(dataInicio) || dataInicio.isBefore(currentDate) || dataVencimento.isBefore(currentDate)) {
                throw new IllegalArgumentException("Data inválida");
            }

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

}
