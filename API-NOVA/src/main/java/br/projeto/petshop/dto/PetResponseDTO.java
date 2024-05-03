package br.projeto.petshop.dto;

import br.projeto.petshop.model.TipoAnimal;
import br.projeto.petshop.model.Usuario;
import br.projeto.petshop.model.Pet;
import br.projeto.petshop.model.Sexo;

public record PetResponseDTO (
    long id,
    String nome,
    Usuario usuario,
    Integer anoNascimentoInteger,
    TipoAnimal tipoAnimal,
    Sexo sexo
){
    public static PetResponseDTO valueOf(Pet pet){
        return new PetResponseDTO(pet.getId(), pet.getNome(), pet.getUsuario(), pet.getAnoNascimento(), pet.getTipoAnimal(), pet.getSexo());
    }
}
