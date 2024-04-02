package br.projeto.petshop.model;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Produto extends DefaultEntity{
    private String nome;
    private Double preco;
    private Integer inventorio;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getInventorio() {
        return inventorio;
    }

    public void setInventorio(Integer inventorio) {
        this.inventorio = inventorio;
    }
}
