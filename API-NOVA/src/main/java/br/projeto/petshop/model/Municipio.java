package br.projeto.petshop.model;

import jakarta.persistence.*;

@Entity
public class Municipio extends DefaultEntity{

    @Column(name = "nome", nullable = false)
    private String nome;

    // Relacionamento com a entidade Estado
    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estadoId;


    public Estado getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Estado estadoId) {
        this.estadoId = estadoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
