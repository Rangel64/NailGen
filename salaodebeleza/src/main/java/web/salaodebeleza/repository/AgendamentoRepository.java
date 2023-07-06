package web.salaodebeleza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.salaodebeleza.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{
    
}
