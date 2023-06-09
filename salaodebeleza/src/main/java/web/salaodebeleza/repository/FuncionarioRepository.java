package web.salaodebeleza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.salaodebeleza.model.Funcionario;
import web.salaodebeleza.repository.helper.funcionario.FuncionarioQueries;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long>, FuncionarioQueries{
    
}
