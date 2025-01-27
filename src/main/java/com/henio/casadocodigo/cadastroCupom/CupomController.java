package com.henio.casadocodigo.cadastroCupom;

import com.henio.casadocodigo.novaCategoria.Categoria;
import com.henio.casadocodigo.novaCategoria.CategoriaResponse;
import com.henio.casadocodigo.novaCategoria.NovaCategoriaRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cupons")
public class CupomController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<CupomResponse> criar(@RequestBody @Valid NovoCupomRequest request) {
        Cupom cupom = request.toModel();
        manager.persist(cupom);
        CupomResponse response = new CupomResponse(cupom);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
}
