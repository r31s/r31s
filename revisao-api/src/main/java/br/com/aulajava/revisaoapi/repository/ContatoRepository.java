package br.com.aulajava.revisaoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.aulajava.revisaoapi.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
    
}