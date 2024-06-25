package com.start.principal.Repository;


import com.start.principal.Model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Clientes, Long> {
}
