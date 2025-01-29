package com.henio.casadocodigo.fechamentoCompra;

import com.henio.casadocodigo.compartilhado.ExistsId;
import com.henio.casadocodigo.novoLivro.Livro;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class NovoItemPedidoRequest {

    @NotNull
    @ExistsId(domainClass = Livro.class, fieldName = "id")
    private Long idLivro;
    @Positive
    private int quantidade;

    public NovoItemPedidoRequest(Long idLivro, int quantidade) {
        this.idLivro = idLivro;
        this.quantidade = quantidade;
    }

    public Long getIdLivro() {
        return idLivro;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public ItemPedido toModel(EntityManager manager) {
      @NotNull Livro livro = manager.find(Livro.class, idLivro);
        return new ItemPedido(livro, quantidade);
    }
}
