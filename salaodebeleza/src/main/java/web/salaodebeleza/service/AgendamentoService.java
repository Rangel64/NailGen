package web.salaodebeleza.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.salaodebeleza.model.Agendamento;
import web.salaodebeleza.repository.AgendamentoRepository;

@Service
public class AgendamentoService  {
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Transactional
    public void salvar(Agendamento agendamento) {
        agendamentoRepository.save(agendamento);
    }
}
