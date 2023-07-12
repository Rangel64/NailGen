package web.salaodebeleza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.salaodebeleza.model.Agendamento;
import web.salaodebeleza.repository.helper.agendamento.AgendamentoQueries;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>, AgendamentoQueries{
    
}
