package web.salaodebeleza.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "servico")
public class ServicoSalao implements Serializable {
    private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="gerador4", sequenceName="pessoa_codigo_seq", allocationSize=1)
	@GeneratedValue(generator="gerador4", strategy=GenerationType.SEQUENCE)
    private Long codigo;
    private String nome;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private Status status = Status.ATIVO;

    @ManyToMany
    @JoinTable(name = "servico_funcionario", joinColumns = @JoinColumn(name = "codigo_servico"), inverseJoinColumns = @JoinColumn(name = "codigo_funcionario"))
    @Size(min = 1, message="O servico deve ter pelo menos um funcionario")
    private List<Funcionario> listaFuncionario = new ArrayList<>();

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

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

    public List<Funcionario> getListaFuncionario() {
        return listaFuncionario;
    }

    public void setListaFuncionario(List<Funcionario> listaFuncionario) {
        this.listaFuncionario = listaFuncionario;
    }

    @Override
    public String toString() {
        return "ServicoSalao [codigo=" + codigo + ", nome=" + nome + ", descricao=" + descricao + ", status=" + status
                + ", listaFuncionario=" + listaFuncionario + "]";
    }
  
    
}
