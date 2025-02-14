package com.henio.casadocodigo.detalheCompra;

import com.henio.casadocodigo.fechamentoCompra.Compra;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("compras")
public class DetalheCompraController {

    @PersistenceContext
    private EntityManager em;

    @GetMapping("/{id}")
    public ResponseEntity<CompraResponse> getById(@PathVariable long id) {
        Compra compra = em.find(Compra.class, id);
        CompraResponse response = new CompraResponse(compra);

        return ResponseEntity.ok(response);
    }
}
