package web.salaodebeleza.repository.helper.funcionario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import web.salaodebeleza.filter.FuncionarioFilter;
import web.salaodebeleza.model.Funcionario;

public interface FuncionarioQueries {
    Page<Funcionario> filtrar(FuncionarioFilter filtro, Pageable pageable);
}
