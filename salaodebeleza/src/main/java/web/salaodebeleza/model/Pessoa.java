package web.salaodebeleza.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "pessoa")
@Data
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="gerador4", sequenceName="pessoa_codigo_seq", allocationSize=1)
	@GeneratedValue(generator="gerador4", strategy=GenerationType.SEQUENCE)
	private Long codigo;
	private String nome;
	private String cpf;
	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;
	private String profissao;
	@Enumerated(EnumType.STRING)
	private Status status = Status.ATIVO;

}