package com.henio.casadocodigo.fechamentoCompra;

import com.henio.casadocodigo.cadastroCupom.Cupom;
import com.henio.casadocodigo.novoEstado.Estado;
import com.henio.casadocodigo.novoPais.Pais;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.util.Assert;

import java.util.function.Function;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String nome;
    private String sobrenome;
    private String documento;
    private String endereco;
    private String complemento;
    private String cidade;
    @ManyToOne
    private Pais pais;
    @ManyToOne
    private Estado estado;
    private String telefone;
    private String cep;
    @OneToOne(mappedBy =  "compra", cascade = CascadeType.PERSIST)
    private Pedido pedido;
    @Embedded
    private CupomAplicado cupomAplicado;

    public Compra(String email, String nome, String sobrenome, String documento, String endereco, String complemento, String cidade, Pais pais, String telefone, String cep, Function<Compra, Pedido> funcaoCriacaoPedido) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.pais = pais;
        this.telefone = telefone;
        this.cep = cep;
        this.pedido = funcaoCriacaoPedido.apply(this);
    }

    @Deprecated
    public Compra() {}

    public Long getId() {
        return id;
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

    public Pais getPais() {
        return pais;
    }

    public Estado getEstado() {
        return estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public CupomAplicado getCupomAplicado() {
        return cupomAplicado;
    }

    public void aplicaCupom(@NotNull @Valid Cupom cupom) {
        Assert.isTrue(cupom.valido(), "O cupom que está sendo aplicado não é válido");
        Assert.isNull(cupomAplicado, "Você não pode trocar um cupom de uma compra");
        this.cupomAplicado = new CupomAplicado(cupom);
    }

    public void setEstado(@NotNull @Valid Estado estado) {
        Assert.notNull(pais, "Não pode selecionar um estado quando o país for nulo");
        Assert.isTrue(estado.pertenceAPais(pais), "Este estado não é do país selecionado");
        this.estado = estado;
    }
}
