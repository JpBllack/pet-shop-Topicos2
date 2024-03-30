package br.projeto.petshop.model;

import jakarta.persistence.Entity;

@Entity
public class Cliente extends Usuario{

    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
