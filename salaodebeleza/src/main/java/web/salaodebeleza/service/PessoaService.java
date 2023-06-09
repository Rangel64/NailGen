package web.salaodebeleza.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.salaodebeleza.model.Pessoa;
import web.salaodebeleza.repository.PessoaRepository;

@Service
public class PessoaService {
    
    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional
    public void salvar(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
    }

    @Transactional
    public void alterar(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
    }

}
