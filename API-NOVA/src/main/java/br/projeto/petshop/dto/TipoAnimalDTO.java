package br.projeto.petshop.dto;

import br.projeto.petshop.model.TipoAnimal;

public record TipoAnimalDTO(
    Long id,
    String nome
) {

    
    public static TipoAnimalDTO valueOf(TipoAnimal tipoAnimal){
        if (tipoAnimal == null) {
            // Você pode lançar uma exceção específica ou retornar um valor padrão aqui
            throw new IllegalArgumentException("O objeto TipoAnimal não pode ser nulo");
        }
        return new TipoAnimalDTO(tipoAnimal.getId(), tipoAnimal.getNome());
    }

    

    public TipoAnimal toModel() {
        TipoAnimal tipoAnimal = new TipoAnimal();
        tipoAnimal.setId(this.id());
        tipoAnimal.setNome(this.nome());
        return tipoAnimal;
    }
    
}
