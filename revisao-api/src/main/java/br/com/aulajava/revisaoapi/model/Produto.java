package br.com.aulajava.revisaoapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Produto{
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, length = 50)
        private String nome;

        @Column(nullable = false, length = 100)
        private String descricao;
        
        @Column(length = 20)
        private String unidade;
        
        @Column(length = 20)
        private Long quantidade;
        
        @Column(length = 3)
        private Double preco;
    }