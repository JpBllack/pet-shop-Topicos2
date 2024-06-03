package br.projeto.petshop.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Compra extends DefaultEntity {
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCompra;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCompra> itensCompra = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StatusCompra> statusCompra = new ArrayList<>();

    private Double precoTotal;

    @Column(name = "usuario_id")
    private Long usuarioId;

    private String endereco;

    private String cartao;

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

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<StatusCompra> getStatusCompra() {
        return statusCompra;
    }

    public void setStatusCompra(List<StatusCompra> statusCompra) {
        this.statusCompra.clear();
        if (statusCompra != null) {
            this.statusCompra.addAll(statusCompra);
        }
    }

    public void addStatusCompra(StatusCompra statusCompra) {
        this.statusCompra.add(statusCompra);
    }

    public void removeStatusCompra(StatusCompra statusCompra) {
        this.statusCompra.remove(statusCompra);
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCartao() {
        return cartao;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }
    
    
}
