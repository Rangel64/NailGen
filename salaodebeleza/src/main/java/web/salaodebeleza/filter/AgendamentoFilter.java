package web.salaodebeleza.filter;

import web.salaodebeleza.model.Dia;
import web.salaodebeleza.model.DiaCliente;
import web.salaodebeleza.model.Funcionario;
import web.salaodebeleza.model.Pessoa;
import web.salaodebeleza.model.ServicoSalao;

public class AgendamentoFilter {
    private Long codigo;
    private Pessoa cliente;
    private Dia codigo_dia_agendamento;
    private DiaCliente codigo_dia_agendamento_cliente;
    private Funcionario funcionario;
    private ServicoSalao servico;
    public Long getCodigo() {
        return codigo;
    }
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
    public Pessoa getCliente() {
        return cliente;
    }
    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }
    public Dia getCodigo_dia_agendamento() {
        return codigo_dia_agendamento;
    }
    public void setCodigo_dia_agendamento(Dia codigo_dia_agendamento) {
        this.codigo_dia_agendamento = codigo_dia_agendamento;
    }
    public DiaCliente getCodigo_dia_agendamento_cliente() {
        return codigo_dia_agendamento_cliente;
    }
    public void setCodigo_dia_agendamento_cliente(DiaCliente codigo_dia_agendamento_cliente) {
        this.codigo_dia_agendamento_cliente = codigo_dia_agendamento_cliente;
    }
    public Funcionario getFuncionario() {
        return funcionario;
    }
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    public ServicoSalao getServico() {
        return servico;
    }
    public void setServico(ServicoSalao servico) {
        this.servico = servico;
    }
    
    
    
}
