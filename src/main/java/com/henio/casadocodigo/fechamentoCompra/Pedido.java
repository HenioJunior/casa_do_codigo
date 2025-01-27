package com.henio.casadocodigo.fechamentoCompra;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Valid
    @OneToOne
    private Compra compra;
    @Size(min = 1)
    @ElementCollection
    private Set<ItemPedido> itens = new HashSet<>();

    public Pedido() {
    }

    public Pedido(@NotNull @Valid Compra compra, @Size(min = 1) Set<ItemPedido> itens) {
        Assert.isTrue(!itens.isEmpty(), "Todo pedido deve ter pelo menos um item");
        this.compra = compra;
        this.itens.addAll(itens);
    }

    public boolean totalIgual(@Positive @NotNull BigDecimal total) {
        BigDecimal totalPedido = itens.stream().map(ItemPedido::total).reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalPedido.doubleValue() == total.doubleValue();
    }

    @Override
    public String toString() {
        return "Pedido{" +
                ", itens=" + itens +
                '}';
    }
}
