package br.projeto.petshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "cartao_credito")
public class CartaoCredito extends DefaultEntity {

    @NotNull
    @Column(name = "numero_cartao", unique = true)
    private String numeroCartao;

    @NotNull
    @Column(name = "codigo_seguranca")
    private String codigoSeguranca;

    @Embedded
    private ValidadeCartao validade;

    @NotNull
    @ManyToOne
    private Usuario usuario;

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public void setCodigoSeguranca(String codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
    }

    public ValidadeCartao getValidade() {
        return validade;
    }

    public void setValidade(ValidadeCartao validade) {
        this.validade = validade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
