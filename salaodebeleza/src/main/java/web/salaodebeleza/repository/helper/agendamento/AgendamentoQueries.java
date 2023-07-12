package web.salaodebeleza.repository.helper.agendamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import web.salaodebeleza.model.Agendamento;

public interface AgendamentoQueries {
    Page<Agendamento> buscarAgendamentosComObjetos(Pageable pageable);
}