package com.henio.casadocodigo.cadastrocategoria;

import com.henio.casadocodigo.compartilhado.UniqueValue;
import jakarta.validation.constraints.NotBlank;

public record NovaCategoriaRequest(
        @NotBlank
        @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
        String nome
) {
    public Categoria toModel() {
        return new Categoria(this.nome);
    }
}
