package br.projeto.petshop.dto;

import br.projeto.petshop.model.Pet;
import br.projeto.petshop.model.Usuario;
import br.projeto.petshop.model.TipoAnimal;

public record PetDTO(
    long id,
    String nome,
    Usuario usuario,
    Integer anoNascimento,
    TipoAnimal tipoAnimal
) {

    public PetDTO {
        // Construtor vazio necessário para que o record funcione
    }


    // Método para converter um objeto Pet em PetDTO
    public static PetDTO valueOf(Pet pet) {
        if (pet == null) {
            return null;
        }

        // Retorna uma nova instância de PetDTO com os dados de Pet
        return new PetDTO(
            pet.getId(),
            pet.getNome(),
            pet.getUsuario(),
            pet.getAnoNascimento(),
            pet.getTipoAnimal()
        );
    }
}
