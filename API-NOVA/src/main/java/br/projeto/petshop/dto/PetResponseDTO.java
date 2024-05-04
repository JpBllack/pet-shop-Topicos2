package br.projeto.petshop.dto;

import java.util.logging.Logger;
import java.util.logging.Level;
import br.projeto.petshop.model.Pet;
import br.projeto.petshop.model.TipoAnimal;
import br.projeto.petshop.model.Usuario;

public record PetResponseDTO(
    Long id,
    String nome,
    Usuario usuario,
    Integer anoNascimento,
    String tipoAnimal
) {
    public static PetResponseDTO valueOf(Pet pet) {
        return new PetResponseDTO(pet.getId(), pet.getNome(), pet.getUsuario(), pet.getAnoNascimento(), pet.getTipoAnimal().getNome());
    }
}
