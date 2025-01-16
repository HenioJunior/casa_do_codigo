package com.henio.casadocodigo.cadastrolivro;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("livros")
public class LivrosController {

    @PersistenceContext
    EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<NovoLivroRequest> cria(@RequestBody @Valid NovoLivroRequest request) {
        Livro livro = request.toModel(manager);
        manager.persist(livro);
        return ResponseEntity.ok(request);
    }
}
