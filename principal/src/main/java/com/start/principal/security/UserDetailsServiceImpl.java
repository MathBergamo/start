package com.start.principal.security;

import com.start.principal.model.Clientes;
import com.start.principal.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClientesRepository clientesRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<Clientes> cliente = clientesRepository.findByEmail(userName);

        if (cliente.isPresent())
            return new UserDetailsImpl(cliente.get());
        else
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

    }
}