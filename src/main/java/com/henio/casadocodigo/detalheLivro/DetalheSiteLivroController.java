package com.henio.casadocodigo.detalheLivro;

import com.henio.casadocodigo.cadastroLivro.Livro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetalheSiteLivroController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/produtos/{id}")
    public ResponseEntity<DetalheSiteLivroResponse> detalheLivro(@PathVariable Long id) {
        Livro livroBuscado = manager.find(Livro.class, id);
        if (livroBuscado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(new DetalheSiteLivroResponse(livroBuscado));
    }
}
