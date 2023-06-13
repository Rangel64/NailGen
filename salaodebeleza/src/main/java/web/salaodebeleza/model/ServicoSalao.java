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
import lombok.Data;

@Entity
@Table(name = "servico")
@Data
public class ServicoSalao implements Serializable {
    private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="gerador5", sequenceName="servico_codigo_seq", allocationSize=1)
	@GeneratedValue(generator="gerador5", strategy=GenerationType.SEQUENCE)
    private Long codigo;
    private String nome;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private Status status = Status.ATIVO;

    @ManyToMany
    @JoinTable(name = "servico_funcionario", joinColumns = @JoinColumn(name = "codigo_servico"), inverseJoinColumns = @JoinColumn(name = "codigo_funcionario"))
    @Size(min = 1, message="O servico deve ter pelo menos um funcionario")
    private List<Funcionario> funcionarios = new ArrayList<>();

    
}
