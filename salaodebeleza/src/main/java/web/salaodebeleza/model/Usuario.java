package web.salaodebeleza.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import web.salaodebeleza.service.NomeUsuarioUnicoService;
import web.salaodebeleza.validation.UniqueValueAttribute;

@Data
@Entity
@Table(name = "usuario")
@UniqueValueAttribute(attribute = "nomeUsuario", service = NomeUsuarioUnicoService.class, message = "Já existe um nome de usuário igual a este cadastrado")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="gerador6", sequenceName="usuario_codigo_seq", allocationSize=1)
	@GeneratedValue(generator="gerador6", strategy=GenerationType.SEQUENCE)
	private Long codigo;
	@NotBlank(message = "O nome do usuário é obrigatório")
	private String nome;
	@Column(name = "cpf")
	@NotNull(message = "O CPF do usuário é obrigatório")
	private String cpf;
	@NotBlank(message = "O e-mail do usuário é obrigatório")
	private String email;
	@NotBlank(message = "A senha do usuário é obrigatória")
	private String senha;
	@Column(name = "nome_usuario")
	@NotBlank(message = "O nome de usuário do usuário é obrigatório")
	private String nomeUsuario;
	@Column(name = "data_nascimento")
	@NotNull(message = "A data de nascimento do usuário é obrigatória")
	private LocalDate dataNascimento;
	
	private boolean ativo;
	@ManyToMany
	@JoinTable(name = "usuario_papel", joinColumns = @JoinColumn(name = "codigo_usuario"), inverseJoinColumns = @JoinColumn(name = "codigo_papel"))
	@Size(min = 1, message = "O usuário deve ter ao menos um papel no sistema")
	private List<Papel> papeis = new ArrayList<>();

	
}