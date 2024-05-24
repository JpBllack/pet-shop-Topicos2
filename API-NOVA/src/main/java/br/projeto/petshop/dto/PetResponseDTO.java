package br.projeto.petshop.dto;

import java.util.logging.Logger;
import java.util.logging.Level;
import br.projeto.petshop.model.Pet;
import br.projeto.petshop.model.TipoAnimal;
import br.projeto.petshop.model.Usuario;

public record PetResponseDTO(
    Long id,
    String nome,
    TipoAnimal tipoAnimal,
    Usuario usuario,
    Integer anoNascimento
) {
    public static PetResponseDTO valueOf(Pet pet) {
        return new PetResponseDTO(
            pet.getId(), 
            pet.getNome(),
            pet.getTipoAnimal(), 
            pet.getUsuario(), 
            pet.getAnoNascimento());
    }
}
