package com.henio.casadocodigo.novoEstado;

import com.henio.casadocodigo.compartilhado.ExistsId;
import com.henio.casadocodigo.compartilhado.UniqueValue;
import com.henio.casadocodigo.novoPais.Pais;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.util.Assert;

public class NovoEstadoRequest {

    @NotBlank
    @UniqueValue(domainClass = Estado.class, fieldName = "nome")
    private String nome;
    @NotNull
    @ExistsId(domainClass = Pais.class, fieldName = "id")
    private Long idPais;

    public NovoEstadoRequest(String nome, Long idPais) {
        this.nome = nome;
        this.idPais = idPais;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdPais() {
        return idPais;
    }

    public Estado toModel(EntityManager manager) {
    Pais pais = manager.find(Pais.class, idPais);
    Assert.state(pais != null, "Você está querendo cadastrar um país para um estado que não existe no banco." + idPais);
    return new Estado(nome, pais);
    }
}
