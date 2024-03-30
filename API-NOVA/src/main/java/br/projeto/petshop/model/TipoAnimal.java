package br.projeto.petshop.model;

import jakarta.persistence.Entity;

@Entity
public class TipoAnimal extends DefaultEntity {

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
