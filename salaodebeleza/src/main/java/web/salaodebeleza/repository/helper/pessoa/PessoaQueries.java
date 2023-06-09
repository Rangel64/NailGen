package web.salaodebeleza.repository.helper.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import web.salaodebeleza.filter.PessoaFilter;
import web.salaodebeleza.model.Pessoa;

public interface PessoaQueries {
    
    Page<Pessoa> filtrar(PessoaFilter filtro, Pageable pageable);

}
