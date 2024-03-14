package org.acme.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Telefone extends DefaultyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "telefone_usuario")
    private Usuario usuario;
    @Size(min = 2, max = 3)
    private String CodigoArea;
    @Size(min = 6, max = 25)
    private String numero;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getCodigoArea() {
        return CodigoArea;
    }
    public void setCodigoArea(String codigoArea) {
        CodigoArea = codigoArea;
    }
  
}
