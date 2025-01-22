package com.henio.casadocodigo.novoEstado;

import com.henio.casadocodigo.novoPais.Pais;

public class EstadoResponse {

    private Long id;
    private String nome;
    private Pais pais;

    public EstadoResponse(Estado estado) {
        this.id = estado.getId();
        this.nome = estado.getNome();
        this.pais = estado.getPais();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Pais getPais() {
        return pais;
    }
}
