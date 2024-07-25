package com.start.transaction.service;

import com.start.transaction.model.Emprestimo;
import com.start.transaction.repository.EmprestimoRepository;
import com.start.transaction.service.kafka.DTO.EmprestimoCadastroDTO;
import com.start.transaction.service.kafka.EmprestimoProducer;
import com.start.transaction.validation.EmprestimoValidation;
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

    @Autowired
    private EmprestimoProducer emprestimoProducer;

    @Autowired
    private EmprestimoValidation emprestimoValidation;

    @Transactional
    public void cadastrarEmprestimo(EmprestimoCadastroDTO dto) {
        Emprestimo emprestimo = modelMapper.map(dto, Emprestimo.class);
        emprestimo.setId(null);

        emprestimoValidation.dataValidator(dto.getDataInicio(), dto.getDataVencimento());

        Emprestimo emprestimoCadastrado = emprestimoRepository.save(emprestimo);

        EmprestimoCadastroDTO respostaDto = modelMapper.map(emprestimoCadastrado, EmprestimoCadastroDTO.class);
        respostaDto.setId(dto.getId());

        emprestimoProducer.enviarEmprestimo(respostaDto);
    }

}
