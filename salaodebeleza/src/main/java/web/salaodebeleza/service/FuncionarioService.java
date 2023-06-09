package web.salaodebeleza.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import web.salaodebeleza.model.Funcionario;
import web.salaodebeleza.repository.FuncionarioRepository;

@Service
public class FuncionarioService {


    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Transactional
    public void salvar(Funcionario funcionario) {
        funcionarioRepository.save(funcionario);
    }

    @Transactional 
    public void alterar(Funcionario funcionario) {
        funcionarioRepository.save(funcionario);
    }
    
}
