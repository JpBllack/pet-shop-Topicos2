package br.projeto.petshop.dto;

import br.projeto.petshop.model.TipoAnimal;

public record TipoAnimalDTO(
    Long id,
    String nome
) {
    public static TipoAnimalDTO valueOf(TipoAnimal tipoAnimal){
        return new TipoAnimalDTO(tipoAnimal.getId(), tipoAnimal.getNome());
    }
}
