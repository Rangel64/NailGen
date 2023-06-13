package web.salaodebeleza.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "papel")
@Data
public class Papel implements Serializable {

	private static final long serialVersionUID = 3377158425416402634L; // gere um outro valor

	@Id
	@SequenceGenerator(name="gerador3", sequenceName="papel_codigo_seq", allocationSize=1)
	@GeneratedValue(generator="gerador3", strategy=GenerationType.SEQUENCE)
	private Long codigo;
	private String nome;

}