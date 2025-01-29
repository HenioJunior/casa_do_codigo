package com.henio.casadocodigo.fechamentoCompra;

import com.henio.casadocodigo.cadastroCupom.Cupom;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

@Embeddable
public class CupomAplicado {

    @ManyToOne
    private Cupom cupom;
    @Positive
    @NotNull
    private BigDecimal percentualDescontoMomento;
    @NotNull
    @Future
    private LocalDate validadeMomento;

    public CupomAplicado() {}

    public CupomAplicado(Cupom cupom) {
        this.cupom = cupom;
        this.percentualDescontoMomento = cupom.getPercentualDesconto();
        this.validadeMomento = cupom.getValidade();
    }

    public Cupom getCupom() {
        return cupom;
    }

    public BigDecimal getPercentualDescontoMomento() {
        return percentualDescontoMomento;
    }

    public LocalDate getValidadeMomento() {
        return validadeMomento;
    }
}
