package com.henio.casadocodigo.novoPais;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("paises")
public class PaisController {

    @Autowired
    private PaisRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<PaisResponse> novoPais(@Valid @RequestBody NovoPaisRequest request) {
        Pais pais = request.toModel(repository);
        repository.save(pais);
        PaisResponse response = new PaisResponse(pais.getId(), pais.getNome());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pais.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
}
