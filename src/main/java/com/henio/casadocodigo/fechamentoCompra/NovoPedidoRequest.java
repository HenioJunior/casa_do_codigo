package com.henio.casadocodigo.fechamentoCompra;

import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NovoPedidoRequest {

    @Positive
    @NotNull
    private BigDecimal total;
    @Size(min = 1)
    @Valid
    private List<NovoItemPedidoRequest> itens = new ArrayList<>();

    public NovoPedidoRequest(BigDecimal total, List<NovoItemPedidoRequest> itens) {
        this.total = total;
        this.itens = itens;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<NovoItemPedidoRequest> getItens() {
        return itens;
    }

    public Function<Compra, Pedido> toModel(EntityManager manager) {

        Set<ItemPedido> itensCalculados = itens.stream().map(item -> item.toModel(manager))
                .collect(Collectors.toSet());

        return (compra) -> {
            Pedido pedido = new Pedido(compra, itensCalculados);
            Assert.isTrue(pedido.totalIgual(total), "O total enviado não corresponde ao total");
            return pedido;
        };
    }
}
