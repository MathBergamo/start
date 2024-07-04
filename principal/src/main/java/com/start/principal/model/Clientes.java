package com.start.principal.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Clientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Clientes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @NotNull(message = "O Atributo Email é Obrigatório!")
    @Column(unique = true)
    @Email(message = "O formato do email é inválido.")
    private String email;

    @NotBlank(message = "O Atributo Senha é Obrigatório!")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    @NotBlank(message = "O Atributo Nome é Obrigatório!")
    private String nome;

    @NotNull(message = "O Atributo Data de Nascimento é Obrigatório!")
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @NotNull(message = "O Atributo Salário é Obrigatório!")
    private BigDecimal salario;

    @CPF(message = "O formato do CPF é inválido.")
    @Column(unique = true)
    private String cpf;

}
