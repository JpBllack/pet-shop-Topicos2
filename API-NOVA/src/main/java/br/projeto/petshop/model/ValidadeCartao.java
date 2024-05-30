package br.projeto.petshop.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class ValidadeCartao{

    @NotNull
    @Min(1)
    @Max(12)
    @Column(name = "validade_mes")
    private Integer mes;

    @NotNull
    @Column(name = "validade_ano")
    private Integer ano;

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    
    
}
