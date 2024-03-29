package br.projeto.petshop.model;

import java.util.Date;

import jakarta.persistence.ManyToOne;

public class Consulta extends DefaultEntity {

    private Date data;
    private String motivo;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Veterinario veterinario;
    @ManyToOne
    private Pet pet;


    public Consulta(Date data, String motivo, Usuario usuario, Veterinario veterinario, Pet pet) {
        this.data = data;
        this.motivo = motivo;
        this.usuario = usuario;
        this.veterinario = veterinario;
        this.pet = pet;
    }


    public Consulta() {
    }


    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }


    public Usuario getUsuario() {
        return usuario;
    }


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public Pet getPet() {
        return pet;
    }


    public void setPet(Pet pet) {
        this.pet = pet;
    }

    
}
