package com.start.transaction.repository;

import com.start.transaction.model.Emprestimo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmprestimoRepository extends MongoRepository<Emprestimo, String> {

}
