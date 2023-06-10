package web.salaodebeleza.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import web.salaodebeleza.model.Dia;
import web.salaodebeleza.repository.DiaRepository;

@Service
public class DiaService {
    @Autowired
    private DiaRepository diaRepository;

    @Transactional
    public void salvar(Dia dia) {
        diaRepository.save(dia);
    }

}



