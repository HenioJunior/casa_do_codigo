package com.henio.casadocodigo.fechamentoCompra;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("compras")
public class FechamentoCompraController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private CupomRepository repository;

    @Autowired
    private EstadoPertenceAPaisValidator estadoPertenceAPaisValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(new VerificaDocumentoCpfCnpjValidator(),
                estadoPertenceAPaisValidator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CompraResponse> cria(@Valid @RequestBody NovaCompraRequest request) {

        Compra  novaCompra = request.toModel(manager, repository);
        manager.persist(novaCompra);

        CompraResponse response = new CompraResponse(novaCompra);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novaCompra.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
