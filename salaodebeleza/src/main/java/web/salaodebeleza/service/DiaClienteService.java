package web.salaodebeleza.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.salaodebeleza.model.DiaCliente;
import web.salaodebeleza.repository.DiaClienteRepository;

@Service
public class DiaClienteService {
    @Autowired
    private DiaClienteRepository diaClienteRepository;

    @Transactional
    public void salvar(DiaCliente dia) {
        diaClienteRepository.save(dia);
    }
    
}
