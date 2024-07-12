package com.start.principal.validation;

import com.start.principal.model.Clientes;
import com.start.principal.model.DTO.ClientesLoginDTO;
import com.start.principal.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class ClientesValidation {

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void nomeValidator(String name) {
        String regex = "^[A-Za-zÇç]+(?: [A-Za-zÇç]+)+$";

        try {
            if (!Pattern.matches(regex, name)) {
                throw new IllegalArgumentException("Nome inválido. Certifique-se de haver nome e sobrenome separados por espaço e não contenha números ou caracteres especiais");
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void senhaValidator(String senha) {
        String regex = "^(?=.*[!@#$%^&*(),.?\":{}|<>])(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$";

        try {
            if (!Pattern.matches(regex, senha)) {
                throw new IllegalArgumentException("Senha inválida, precisa conter 1 carácter especial, 1 carácter maiúsculo, 1 carácter minúsculo e 1 número");
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void emailValidator(String email) {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

        try {
            if (!Pattern.matches(regex, email)) {
                throw new IllegalArgumentException("Email inválido. Certifique-se de colocar um email com formato correto");
            }

            if (clientesRepository.existsByEmail(email)) {
                throw new IllegalArgumentException("Email já registrado, tente outro");
            }

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void dataNascimentoValidator(LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        LocalDate minimumDateOfBirth = currentDate.minusYears(18);

        try {
            if (date.isAfter(currentDate)) {
                throw new IllegalArgumentException("Data inválida");
            }

            if (date.isAfter(minimumDateOfBirth)) {
                throw new IllegalArgumentException("Idade não permitida. O usuário precisa ter no mínimo 18 anos");
            }

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizarClienteValidator(Long id) {

        try {
            if (clientesRepository.findById(id).isPresent()) {
                Optional<Clientes> buscaCliente = clientesRepository.findById(id);

                if ((buscaCliente.isPresent()) && (!buscaCliente.get().getId().equals(id))) {
                    throw new DataIntegrityViolationException("Usuário já existente");
                }
            }

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(e.getMessage());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void autenticarClienteValidator(ClientesLoginDTO dto) {
        var credenciais = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha());

        try {
            Authentication authentication = authenticationManager.authenticate(credenciais);

            if (!authentication.isAuthenticated()) {
                throw new AuthenticationException("Erro de autenticação");
            }

        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Credenciais inválidas.");
        } catch (AuthenticationException e) {
            throw new RuntimeException();
        }
    }
}
