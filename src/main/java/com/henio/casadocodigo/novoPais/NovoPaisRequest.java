package com.henio.casadocodigo.novoPais;

import com.henio.casadocodigo.compartilhado.UniqueValue;
import jakarta.validation.constraints.NotBlank;
import org.springframework.util.Assert;

import java.util.Optional;

public class NovoPaisRequest {

    @NotBlank
    @UniqueValue(domainClass = Pais.class, fieldName = "nome")
    private String nome;

    public NovoPaisRequest() {
    }

    public NovoPaisRequest(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Pais toModel() {
        return new Pais(nome);
    }
}
