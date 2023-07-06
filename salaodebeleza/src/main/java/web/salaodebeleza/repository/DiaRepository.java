package web.salaodebeleza.repository;




import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import web.salaodebeleza.model.Dia;
import web.salaodebeleza.model.Funcionario;

public interface DiaRepository extends JpaRepository<Dia, Long> {
    
    Dia findByDataAgendamentoAndFuncionario(LocalDate dataAgendamento, Funcionario funcionario);

}
