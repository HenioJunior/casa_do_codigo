package com.henio.cdc.novacategoria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categorias")
public class NovaCategoriaController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private ProibeCategoriaDuplicadaValidator proibeCategoriaDuplicadaValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(proibeCategoriaDuplicadaValidator);
    }

    @PostMapping
    @Transactional
    public String criar(@RequestBody @Valid NovaCategoriaRequest request) {
        Categoria categoria = request.toModel();
        manager.persist(categoria);
        return categoria.toString();
    }
}
