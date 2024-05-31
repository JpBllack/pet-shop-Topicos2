package br.projeto.petshop.model;

import jakarta.persistence.*;

@Entity
public class Municipio extends DefaultEntity{

    @Column(name = "nome", nullable = false)
    private String nome;

    // Relacionamento com a entidade Estado
    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estado;



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}
