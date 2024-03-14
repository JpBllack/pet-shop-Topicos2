package org.acme.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Produto extends DefaultyEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotBlank(message = "Informe o nome do produto")
    @Size(max = 100, message = "o campo 'nome' tem que ter no maximo 100 caracteres")
    private String nome;

    @NotBlank(message = "Descreva o produto")
    @Size(max = 280, message = "o campo 'descricao' tem que ter no maximo 280 caracteres")
    private String descricao;

    @NotNull
    private Double valor;

    private String imagem;

    @Column(name = "quantidade_estoque")
    private Integer quantEstoque;

    @ManyToOne
    @JoinColumn(name = "categoria_produto")
    private Categoria categoria;

    @OneToMany
    @JoinColumn(name = "produto_avaliacao")
    private List<Avaliacao> avaliacaoList;

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public List<Avaliacao> getAvaliacaoList() {
        if(avaliacaoList == null){
            avaliacaoList = new ArrayList<>();
        }
        return avaliacaoList;
    }

    public void setAvaliacaoList(List<Avaliacao> avaliacaoList) {
        this.avaliacaoList = avaliacaoList;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getQuantEstoque() {
        return quantEstoque;
    }

    public void setQuantEstoque(Integer quantEstoque) {
        this.quantEstoque = quantEstoque;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

