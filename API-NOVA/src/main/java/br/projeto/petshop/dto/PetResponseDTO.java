package br.projeto.petshop.dto;

import br.projeto.petshop.model.TipoAnimal;
import br.projeto.petshop.model.Pet;
import br.projeto.petshop.model.Sexo;

public record PetResponseDTO (
    String nome,
    Integer anoNascimentoInteger,
    TipoAnimal tipoAnimal,
    Sexo sexo
){
    public static PetResponseDTO valueOf(Pet pet){
        return new PetResponseDTO(pet.getNome(), pet.getAnoNascimento(), pet.getTipoAnimal(), pet.getSexo());
    }
}
