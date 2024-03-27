package org.acme.dto;

import org.acme.model.Animal;
import org.acme.model.Sexo;

public class PetDTO {
    private Long id;
    private String nome;
    private int anoNascimento;
    private Animal tipoAnimal;
    private Sexo sexo;

   
    public PetDTO() {}

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
