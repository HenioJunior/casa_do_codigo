package com.henio.casadocodigo.detalheLivro;

import com.henio.casadocodigo.novaCategoria.CategoriaResponse;
import com.henio.casadocodigo.novoLivro.Livro;
import com.henio.casadocodigo.novoAutor.AutorResponse;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public class DetalheSiteLivroResponse {

    private Long id;
    private String titulo;
    private String resumo;
    private String sumario;
    private BigDecimal preco;
    private Integer numeroPaginas;
    private String isbn;
    private String dataPublicacao;
    private AutorResponse autor;
    private CategoriaResponse categoria;

    public DetalheSiteLivroResponse(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
        this.preco = livro.getPreco();
        this.numeroPaginas = livro.getNumeroPaginas();
        this.isbn = livro.getIsbn();
        this.dataPublicacao = livro.getDataPublicacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.autor = new AutorResponse(livro.getAutor());
        this.categoria = new CategoriaResponse(livro.getCategoria());
    }

    public Long getId() {
        return id;
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

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public AutorResponse getAutor() {
        return autor;
    }

    public CategoriaResponse getCategoria() {
        return categoria;
    }
}
