package web.salaodebeleza.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "agendamento")
@Data
public class Agendamento {

	@Id
	@SequenceGenerator(name="gerador7", sequenceName="agendamento_codigo_seq", allocationSize=1)
	@GeneratedValue(generator="gerador7", strategy=GenerationType.SEQUENCE)
	private Long codigo;
    
    @JoinColumn(name="codigo_cliente")
    @ManyToOne
    private Pessoa cliente;

    @JoinColumn(name="codigo_dia_agendamento")
    @ManyToOne(fetch = FetchType.LAZY)
    private Dia codigo_dia_agendamento;

    @JoinColumn(name="codigo_dia_agendamento_cliente")
    @ManyToOne(fetch = FetchType.LAZY)
    private DiaCliente codigo_dia_agendamento_cliente;

    @JoinColumn(name="codigo_funcionario")
    @ManyToOne(fetch = FetchType.LAZY)
    private Funcionario funcionario;

    @JoinColumn(name="codigo_servico")
    @ManyToOne(fetch = FetchType.LAZY)
    private ServicoSalao servico;

}
