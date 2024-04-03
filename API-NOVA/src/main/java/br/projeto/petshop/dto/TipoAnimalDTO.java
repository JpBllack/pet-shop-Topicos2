package br.projeto.petshop.dto;

import br.projeto.petshop.model.TipoAnimal;

public record TipoAnimalDTO(
    String nome
) {
    public static TipoAnimalDTO valueOf(TipoAnimal tipoAnimal){
        return new TipoAnimalDTO(tipoAnimal.getNome());
    }
}
