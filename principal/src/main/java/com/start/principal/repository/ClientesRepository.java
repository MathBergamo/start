package com.start.principal.repository;

import com.start.principal.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Long> {

    Optional<Clientes> findByEmail(String email);

}
