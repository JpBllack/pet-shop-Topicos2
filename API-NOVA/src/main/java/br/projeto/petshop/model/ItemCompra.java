package br.projeto.petshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ItemCompra extends DefaultEntity {

    private String nome;
    private double precoUnitario;
    private int quantidade;
    
    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compra compra;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public double getPrecoUnitario() {
        return precoUnitario;
    }
    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public Compra getCompra() {
        return compra;
    }
    public void setCompra(Compra compra) {
        this.compra = compra;
    }
    
    
}
