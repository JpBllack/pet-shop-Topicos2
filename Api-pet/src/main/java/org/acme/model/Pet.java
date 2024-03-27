package org.acme.model;

public class Pet {
    private String nome;
    private int anoNascimento;
    private Animal tipoAnimal;
    private Sexo sexo;

    public Pet(String nome, int anoNascimento, Animal tipoAnimal, Sexo sexo) {
        this.nome = nome;
        this.anoNascimento = anoNascimento;
        this.tipoAnimal = tipoAnimal;
        this.sexo = sexo;
    }

    // Getters e Setters para nome, anoNascimento, tipoAnimal e sexo

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

    public Animal getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(Animal tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }
}
