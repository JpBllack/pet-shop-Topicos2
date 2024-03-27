package org.acme.model;

public class Racao {
    private String sabor;
    private Animal animal;
    private Peso peso;
    private Idade idade;

    public Racao(String sabor, Animal animal, Peso peso, Idade idade) {
        this.sabor = sabor;
        this.animal = animal;
        this.peso = peso;
        this.idade = idade;
    }

    // Getters e Setters para sabor, animal, peso e idade

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Peso getPeso() {
        return peso;
    }

    public void setPeso(Peso peso) {
        this.peso = peso;
    }

    public Idade getIdade() {
        return idade;
    }

    public void setIdade(Idade idade) {
        this.idade = idade;
    }
}
