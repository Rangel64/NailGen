package web.salaodebeleza.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
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
@Table(name = "dia")
@Data
public class Dia {
    
    @Id
	@SequenceGenerator(name="gerador1", sequenceName="dia_codigo_seq", allocationSize=1)
	@GeneratedValue(generator="gerador1", strategy=GenerationType.SEQUENCE)

    private Long codigo;
    private String nome_dia;

    @Column(name = "data_agendamento")
    private LocalDate data_agendamento;

    private Boolean h_7 = false;
    private Boolean h_8 = false;
    private Boolean h_9 = false;
    private Boolean h_10 = false;
    private Boolean h_11 = false;
    private Boolean h_12 = false;
    private Boolean h_13 = false;
    private Boolean h_14 = false;
    private Boolean h_15 = false;
    private Boolean h_16 = false;
    private Boolean h_17 = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="codigo_funcionario")
    private Funcionario funcionario;

}
