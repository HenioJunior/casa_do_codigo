package com.henio.cdc.novacategoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProibeCategoriaDuplicadaValidator implements Validator {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovaCategoriaRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }
        NovaCategoriaRequest request = (NovaCategoriaRequest) target;
        Optional<Categoria> possivelCategoria = categoriaRepository.findByNome(request.nome());
        if(possivelCategoria.isPresent()) {
            errors.rejectValue("nome", null,
                    "Já existe uma categoria com o mesmo nome "
                            + request.nome());
        }
    }
}
