package web.salaodebeleza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import web.salaodebeleza.model.Funcionario;
import web.salaodebeleza.model.Status;
import web.salaodebeleza.repository.helper.funcionario.FuncionarioQueries;

public interface FuncionarioRepository extends JpaRepository<Funcionario,Long>, FuncionarioQueries{
    public List<Funcionario> findByStatus(Status status);
}
