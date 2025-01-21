package com.henio.casadocodigo.novoAutor;

import java.time.format.DateTimeFormatter;

public class AutorResponse {
    private Long id;
    private String nome;
    private String email;
    private String descricao;
    private String instanteCriacao;


    public AutorResponse(Autor autor) {
        this.id = autor.getId();
        this.nome = autor.getNome();
        this.email = autor.getEmail();
        this.descricao = autor.getDescricao();
        this.instanteCriacao = autor.getInstanteCriacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getInstanteCriacao() {
        return instanteCriacao;
    }
}
