package web.salaodebeleza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.salaodebeleza.model.ServicoSalao;
import web.salaodebeleza.repository.helper.servicoSalao.ServicoSalaoQueries;

public interface ServicoRepository extends JpaRepository<ServicoSalao, Long>,ServicoSalaoQueries{
    
}
