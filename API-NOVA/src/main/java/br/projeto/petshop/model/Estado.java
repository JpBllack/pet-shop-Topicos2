package br.projeto.petshop.model;


import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Estado extends DefaultEntity {

    @Column(length = 60)
    private String nome;

    @Column(length = 2)
    private String sigla;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }


    
}
