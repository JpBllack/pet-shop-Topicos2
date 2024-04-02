package br.projeto.petshop.dto;

public class RacaoResponseDTO {
    private Long id;
    private String sabor;
    private String tipoAnimal;

    // Construtores, getters e setters

    public RacaoResponseDTO() {
    }

    public RacaoResponseDTO(Long id, String sabor, String tipoAnimal) {
        this.id = id;
        this.sabor = sabor;
        this.tipoAnimal = tipoAnimal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public String getTipoAnimal() {
        return tipoAnimal;
    }

    public void setTipoAnimal(String tipoAnimal) {
        this.tipoAnimal = tipoAnimal;
    }
}
