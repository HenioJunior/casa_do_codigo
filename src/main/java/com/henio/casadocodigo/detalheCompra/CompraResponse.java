package com.henio.casadocodigo.detalheCompra;

import com.henio.casadocodigo.fechamentoCompra.Compra;
import com.henio.casadocodigo.fechamentoCompra.ItemPedido;

import java.math.BigDecimal;
import java.util.Set;

public class CompraResponse {

    private boolean existeCupom;
    private BigDecimal total;

    public CompraResponse(Compra compra) {
        BigDecimal percentualDesconto;
        Set<ItemPedido> itens = compra.getPedido().getItens();
        BigDecimal totalPedido = itens.stream().map(ItemPedido::total).reduce(BigDecimal.ZERO, BigDecimal::add);

        if(compra.existeCupom()) {
            this.existeCupom = true;
            percentualDesconto = compra.getCupomAplicado().getPercentualDescontoMomento();
            BigDecimal porcentagem = percentualDesconto.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
            BigDecimal p = porcentagem.add(new BigDecimal(1));

            this.total = totalPedido.multiply(p);;
        } else {
            this.existeCupom = false;
            this.total = totalPedido;
        }
    }

    public boolean isExisteCupom() {
        return existeCupom;
    }

    public BigDecimal getTotal() {
        return total;
    }
}

