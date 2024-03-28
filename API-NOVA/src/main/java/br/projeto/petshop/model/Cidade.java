package br.projeto.petshop.model;


import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Cidade extends DefaultEntity{

    @Column(length = 80, nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "usuario_estado")
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
