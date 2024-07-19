package com.start.transaction.service;

import com.start.transaction.model.Emprestimo;
import com.start.transaction.repository.EmprestimoRepository;
import com.start.transaction.service.kafka.DTO.EmprestimoCadastroDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public void cadastrarEmprestimo(EmprestimoCadastroDTO dto) {
        Emprestimo emprestimoCadastrado = emprestimoRepository.save(modelMapper.map(dto, Emprestimo.class));
    }

}
