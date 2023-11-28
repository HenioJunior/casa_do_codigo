package com.henio.cdc.dto;

import com.henio.cdc.entity.Autor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.util.Assert;

public class AutorRequest {
    @NotBlank
    private String nome;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(max = 400)
    private String descricao;

    public AutorRequest(@NotBlank String nome, @NotBlank @Email String email,
                        @NotBlank @Size(max = 400) String descricao) {
        Assert.hasLength(nome, "O nome é obrigatório");
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public Autor toModel() {
        return new Autor(this.nome, this.email, this.descricao);
    }
}
