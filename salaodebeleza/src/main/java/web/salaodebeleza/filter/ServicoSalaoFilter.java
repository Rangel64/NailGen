package web.salaodebeleza.filter;

import java.util.ArrayList;
import java.util.List;

import web.salaodebeleza.model.Funcionario;
import web.salaodebeleza.model.Status;

public class ServicoSalaoFilter {
    private Long codigo;
    private String nome;
    private String descricao;
    private Status status = Status.ATIVO;
    private List<Funcionario> funcionarios = new ArrayList<>();

    
    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }
    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
    
}
