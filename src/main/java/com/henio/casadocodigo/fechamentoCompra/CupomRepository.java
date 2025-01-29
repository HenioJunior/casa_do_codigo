package com.henio.casadocodigo.fechamentoCompra;

import com.henio.casadocodigo.cadastroCupom.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CupomRepository extends JpaRepository<Cupom, Long> {
    public Cupom getByCodigo(String codigo);

}
