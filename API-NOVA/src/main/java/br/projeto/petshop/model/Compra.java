package br.projeto.petshop.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

public class Compra extends DefaultEntity {
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCompra;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "compra_id")
    private List<ItemCompra> itensCompra;

    private Double precoTotal;

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
        this.itensCompra = itensCompra;
    }

    public Double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(Double precoTotal) {
        this.precoTotal = precoTotal;
    }

}
