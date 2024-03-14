package org.acme.model;


import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Estado extends DefaultyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(length = 60)
    private String nome;

    @Column(length = 2)
    private String sigla;


    public String getnome() {
        return nome;
    }

    public void setnome(String nome) {
        this.nome = nome;
    }

    public String getsigla() {
        return sigla;
    }

    public void setsigla(String sigla) {
        this.sigla = sigla;
    }


    
}
