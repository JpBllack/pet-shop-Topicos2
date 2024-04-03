package br.projeto.petshop.model;

import java.util.Date;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Consulta extends DefaultEntity {

    private Date data;
    private String motivo;
    @ManyToOne
    private Usuario veterinario;
    @ManyToOne
    private Pet pet;

    public Consulta(Date data, String motivo, Usuario veterinario, Pet pet) {
        this.data = data;
        this.motivo = motivo;
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

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Usuario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Usuario veterinario) {
        this.veterinario = veterinario;
    }

}
