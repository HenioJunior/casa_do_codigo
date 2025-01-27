package com.henio.casadocodigo.fechamentoCompra;

import com.henio.casadocodigo.novoLivro.Livro;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class ItemPedido {
    @ManyToOne
    private Livro livro;
    @Positive
    private BigDecimal precoDoMomento;
    @Positive
    private int quantidade;

    @Deprecated
    public ItemPedido() {}

    public ItemPedido(Livro livro, int quantidade) {
        this.livro = livro;
        this.quantidade = quantidade;
        this.precoDoMomento = livro.getPreco();
    }

    public Livro getLivro() {
        return livro;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal total() {
        return precoDoMomento.multiply(new BigDecimal(quantidade));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return Objects.equals(livro, that.livro);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(livro);
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "livro=" + livro +
                ", precoDoMomento=" + precoDoMomento +
                ", quantidade=" + quantidade +
                '}';
    }
}
