package com.henio.casadocodigo.cadastroCategoria;

import com.henio.casadocodigo.compartilhado.UniqueValue;
import jakarta.validation.constraints.NotBlank;

public class CategoriaResponse {

    private Long id;
    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    public CategoriaResponse(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Categoria toModel() {
        return new Categoria(this.nome);
    }
}
