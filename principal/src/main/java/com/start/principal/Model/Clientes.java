package com.start.principal.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.start.principal.Model.DTO.ClientesDTO;
import com.start.principal.Model.enums.ClientesCargo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "cargo", nullable = false)
    @Enumerated(EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ClientesCargo cargo;

    @NotBlank
    private String nome;

    @NotNull
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @NotNull
    private BigDecimal salario;

    @NotBlank
    @Column(unique = true)
    private String cpf;

    public Clientes(Clientes clientes) {
    }

    public Clientes(ClientesDTO dto) {
        this.cargo = dto.getCargo();
        this.nome = dto.getNome();
        this.dataNascimento = dto.getDataNascimento();
        this.cpf = dto.getCpf();
        this.salario = dto.getSalario();
    }
}
