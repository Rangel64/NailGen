package web.salaodebeleza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.salaodebeleza.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByNomeUsuarioIgnoreCase(String nomeUsuario);
	
}
