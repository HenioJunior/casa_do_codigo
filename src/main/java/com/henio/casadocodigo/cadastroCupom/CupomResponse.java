package com.henio.casadocodigo.cadastroCupom;


import java.math.BigDecimal;
import java.time.LocalDate;

public class CupomResponse {
    private Long id;
    private String codigo;
    private BigDecimal percentualDesconto;
    private LocalDate validade;

    public CupomResponse(Cupom cupom) {
        this.id = cupom.getId();
        this.codigo = cupom.getCodigo();
        this.percentualDesconto = cupom.getPercentualDesconto();
        this.validade = cupom.getValidade();
    }

    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public BigDecimal getPercentualDesconto() {
        return percentualDesconto;
    }

    public LocalDate getValidade() {
        return validade;
    }
}
