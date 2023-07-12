package web.salaodebeleza.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import web.salaodebeleza.model.DiaCliente;
import web.salaodebeleza.model.Pessoa;

public interface DiaClienteRepository extends JpaRepository<DiaCliente, Long>{

    DiaCliente findByDataAgendamentoAndCliente(LocalDate dataAgendamento, Pessoa cliente);
    
}
