package com.henio.casadocodigo.fechamentoCompra;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("compras")
public class FechamentoCompraController {

    @Autowired
    private EstadoPertenceAPaisValidator estadoPertenceAPaisValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(new VerificaDocumentoCpfCnpjValidator(),
                estadoPertenceAPaisValidator);
    }

    @PostMapping
    @Transactional
    public String cria(@Valid @RequestBody NovaCompraRequest request) {
        return request.toString();
    }
}
