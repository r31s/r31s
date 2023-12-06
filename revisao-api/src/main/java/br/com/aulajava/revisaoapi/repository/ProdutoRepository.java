package br.com.aulajava.revisaoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.aulajava.revisaoapi.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long> {
    
}

