package br.projeto.petshop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Veterinario extends Usuario {

    private String crvm;

    public String getCrvm() {
        return crvm;
    }

    public void setCrvm(String crvm) {
        this.crvm = crvm;
    }

    
}
