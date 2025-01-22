package com.henio.casadocodigo.fechamentoCompra;

import com.henio.casadocodigo.compartilhado.ExistsId;
import com.henio.casadocodigo.novoEstado.Estado;
import com.henio.casadocodigo.novoPais.Pais;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;

public class NovaCompraRequest {

    @NotBlank
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;
    @NotBlank
    private String documento;
    @NotBlank
    private String endereco;
    @NotBlank
    private String complemento;
    @NotBlank
    private String cidade;
    @NotNull
    @ExistsId(domainClass = Pais.class, fieldName = "id")
    private Long idPais;
    @ExistsId(domainClass = Estado.class, fieldName = "id")
    private Long idEstado;
    @NotBlank
    private String telefone;
    @NotBlank
    private String cep;

    public NovaCompraRequest(String email, String nome, String sobrenome, String documento, String endereco,
                             String complemento, String cidade, Long idPais, Long idEstado, String telefone,
                             String cep) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.idPais = idPais;
        this.idEstado = idEstado;
        this.telefone = telefone;
        this.cep = cep;

    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public Long getIdPais() {
        return idPais;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public Compra toModel(EntityManager manager) {
        Pais pais = manager.find(Pais.class, idPais);
        Compra compra = new Compra(email, nome, sobrenome, documento, endereco, complemento, cidade, pais, telefone, cep);
        if(idEstado != null) {
            compra.setEstado(manager.find(Estado.class, idEstado));
        }
        return compra;
    }

    public boolean documentoValido() {
        Assert.hasLength(documento,
                "Você não deveria validar o documento se ele não tiver sido preenchido.");

        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);

        CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);

        return cpfValidator.isValid(documento, null)
                || cnpjValidator.isValid(documento, null);
    }

    public boolean temEstado() {
       return idEstado != null;
    }
}
