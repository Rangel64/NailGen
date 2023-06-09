package web.salaodebeleza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.salaodebeleza.model.Pessoa;
import web.salaodebeleza.repository.helper.pessoa.PessoaQueries;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PessoaQueries {
    
}
