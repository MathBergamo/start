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

//    @Column(name = "cargo", nullable = false)
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    private ClientesCargo cargo;

    @NotBlank
    private String nome;

    @NotNull
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @NotNull
    private BigDecimal salario;

    @NotBlank
    private String cpf;

    @NotBlank
    private String email;

    @NotBlank
    @JsonIgnore
    private String senha;


    public Clientes(Long id, String nome, LocalDate dataNascimento, String cpf, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    public Clientes(ClientesDTO dto) {
        this.nome = dto.getNome();
        this.dataNascimento = dto.getDataNascimento();
        this.cpf = dto.getCpf();
        this.email = dto.getEmail();
        this.senha = dto.getSenha();
        this.salario = dto.getSalario();
    }
}
