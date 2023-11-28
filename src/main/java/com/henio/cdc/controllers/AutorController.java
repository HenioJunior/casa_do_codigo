package com.henio.cdc.controllers;

import com.henio.cdc.dto.AutorRequest;
import com.henio.cdc.entity.Autor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public String novoAutor(@RequestBody @Valid AutorRequest request) {
        Autor autor = request.toModel();
        manager.persist(autor);
        return autor.toString();
    }
}

