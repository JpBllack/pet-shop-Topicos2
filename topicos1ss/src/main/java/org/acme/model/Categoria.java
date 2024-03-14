package org.acme.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Categoria extends DefaultyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @NotNull
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}