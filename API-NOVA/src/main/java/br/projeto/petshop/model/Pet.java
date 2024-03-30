package br.projeto.petshop.model;

import jakarta.persistence.ManyToOne;

public class Pet extends DefaultEntity {
    private String nome;
    private int anoNascimento;

    @ManyToOne
    private TipoAnimal tipoAnimal;

    private Sexo sexo;

    // Getters e Setters para nome, anoNascimento, tipoAnimal, sexo

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public TipoAnimal getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(TipoAnimal tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }
}
