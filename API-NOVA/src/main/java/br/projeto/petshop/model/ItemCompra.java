package br.projeto.petshop.model;

import jakarta.persistence.ManyToOne;

public class ItemCompra extends DefaultEntity {

    @ManyToOne
    private Racao racao;

    private Double precoUnitario;
    private Integer quantidade;

    public Racao getRacao() {
        return racao;
    }

    public void setRacao(Racao racao) {
        this.racao = racao;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

}
