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


    public Consulta(Date data, String motivo, Usuario usuario, Veterinario veterinario) {
        this.data = data;
        this.motivo = motivo;
        this.usuario = usuario;
        this.veterinario = veterinario;
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

    public Usuario getCliente() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }
}
