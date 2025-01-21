package com.henio.casadocodigo.novoLivro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("livros")
public class LivroController {

    @PersistenceContext
    EntityManager manager;

    @GetMapping
    public List<ListarLivrosResponse> listarLivros(){
        List<Object[]> list = manager.createQuery("SELECT a.id, a.titulo FROM Livro a", Object[].class).getResultList();
        return list.stream().map(ListarLivrosResponse::new).collect(Collectors.toList());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<LivroResponse> cria(@RequestBody @Valid NovoLivroRequest request) {
        Livro livro = request.toModel(manager);
        manager.persist(livro);
        LivroResponse response = new LivroResponse(livro);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livro.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
}
