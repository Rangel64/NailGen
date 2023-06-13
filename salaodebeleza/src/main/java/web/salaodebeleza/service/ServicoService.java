package web.salaodebeleza.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import web.salaodebeleza.model.ServicoSalao;
import web.salaodebeleza.repository.ServicoRepository;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;
    
    @Transactional
    public void salvar(ServicoSalao servico) {
        servicoRepository.save(servico);
    }

    @Transactional
    public void alterar(ServicoSalao servico) {
        servicoRepository.save(servico);
    }
    
}
