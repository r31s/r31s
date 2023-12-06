package br.com.aulajava.revisaoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aulajava.revisaoapi.model.Contato;
import br.com.aulajava.revisaoapi.repository.ContatoRepository;

@RestController
@RequestMapping("/contatos")
@CrossOrigin
public class ContatoController {
    @Autowired
    private ContatoRepository repositorio;

    @GetMapping
    public List<Contato> listar(){
        return repositorio.findAll();
    }

    @PostMapping
    public Contato adicionar(@RequestBody Contato contato){
        return repositorio.save(contato);
    }

    @PutMapping
    public Contato alterar(@RequestBody Contato contato){
        if(contato.getId() != null && contato.getId() > 0) {
            Contato contatoExistente = repositorio.findById(contato.getId()).orElse(null);
            if (contatoExistente != null) {
                contatoExistente.setNome(contato.getNome());
                contatoExistente.setEmail(contato.getEmail());
                contatoExistente.setTelefone(contato.getTelefone());

                return repositorio.save(contatoExistente);
            }
        }
        return null;
}

    @DeleteMapping
    public String excluir(@RequestBody Contato contato){
            if(contato.getId()>0){
                repositorio.delete(contato);
                return "Contato removido com sucesso";
            }
        return "Contanto não encontrado";
    }
}