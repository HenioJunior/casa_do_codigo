package com.henio.casadocodigo.novoLivro;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.henio.casadocodigo.novaCategoria.Categoria;
import com.henio.casadocodigo.compartilhado.UniqueValue;
import com.henio.casadocodigo.novoAutor.Autor;
import jakarta.persistence.EntityManager;
import jakarta.validation.constraints.*;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;

public class NovoLivroRequest {

    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "titulo")
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String resumo;
    private String sumario;
    @NotNull
    @Min(value = 20)
    private BigDecimal preco;
    @NotNull
    @Min(value = 100)
    private Integer numeroPaginas;
    @NotBlank
    private String isbn;
    @Future
    @NotNull
    @JsonFormat(pattern = "dd-MM-yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataPublicacao;
    @NotNull
    private Integer idAutor;
    @NotNull
    private Integer idCategoria;

    public NovoLivroRequest() {
    }

    public NovoLivroRequest(String titulo, String resumo, String sumario, BigDecimal preco, Integer numeroPaginas, String isbn, LocalDate dataPublicacao, Integer idAutor, Integer idCategoria) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.idAutor = idAutor;
        this.idCategoria = idCategoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public Integer getIdAutor() {
        return idAutor;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public Livro toModel(EntityManager manager) {
        @NotNull Autor autor = manager.find(Autor.class, idAutor);
        @NotNull Categoria categoria = manager.find(Categoria.class, idCategoria);

        Assert.state(autor != null, "Você está querendo cadastrar um livro para um autor que não existe no banco." + idAutor);
        Assert.state(categoria != null, "Você está querendo cadastrar um livro para uma categoria que não existe no banco." + idAutor);

        return new Livro(titulo, resumo, sumario, preco, numeroPaginas, isbn, dataPublicacao,
                autor, categoria);
    }
}
