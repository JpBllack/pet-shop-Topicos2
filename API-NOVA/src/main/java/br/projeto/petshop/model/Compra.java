package br.projeto.petshop.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Compra extends DefaultEntity {
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCompra;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCompra> itensCompra = new ArrayList<>();

    private Double precoTotal;

    // Getters and Setters

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public List<ItemCompra> getItensCompra() {
        return itensCompra;
    }

    public void setItensCompra(List<ItemCompra> itensCompra) {
        this.itensCompra.clear();
        if (itensCompra != null) {
            this.itensCompra.addAll(itensCompra);
            for (ItemCompra item : itensCompra) {
                item.setCompra(this);
            }
        }
    }

    public void addItemCompra(ItemCompra itemCompra) {
        this.itensCompra.add(itemCompra);
        itemCompra.setCompra(this);
    }

    public void removeItemCompra(ItemCompra itemCompra) {
        this.itensCompra.remove(itemCompra);
        itemCompra.setCompra(null);
    }

    public Double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(Double precoTotal) {
        this.precoTotal = precoTotal;
    }
}
