package org.acme.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Venda extends DefaultyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Double valorTotal;

    @OneToMany
    @JoinColumn(name = "venda_itemvenda")
    private List<ItemVenda> itemVendaList;

    @OneToOne
    @JoinColumn(name = "venda_pagamento")
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name = "endereco_venda")
    private Endereco endereco;

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Double getValorTotal() {
        if(valorTotal == null){
            valorTotal = 0.0;
        }
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<ItemVenda> getItemVendaList() {
        if(itemVendaList == null){
            itemVendaList = new ArrayList<>();
        }

        return itemVendaList;
    }

    public void setItemVendaList(List<ItemVenda> itemVendaList) {
        this.itemVendaList = itemVendaList;
    }
}
