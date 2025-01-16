package com.henio.casadocodigo.cadastroCategoria;

import com.henio.casadocodigo.compartilhado.UniqueValue;
import jakarta.validation.constraints.NotBlank;

public class NovaCategoriaRequest {

    private Long id;
    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    public String getNome() {
        return nome;
    }

    public Categoria toModel() {
        return new Categoria(this.nome);
    }
}
