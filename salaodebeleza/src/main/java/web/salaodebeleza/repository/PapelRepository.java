package web.salaodebeleza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.salaodebeleza.model.Papel;

public interface PapelRepository extends JpaRepository<Papel, Long> {
    
}
