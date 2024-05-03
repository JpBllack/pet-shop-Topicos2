package br.projeto.petshop.dto;

import java.util.logging.Logger;
import java.util.logging.Level;
import br.projeto.petshop.model.Pet;
import br.projeto.petshop.model.TipoAnimal;
import br.projeto.petshop.model.Usuario;

public record PetResponseDTO(
    long id,
    String nome,
    UsuarioResponseDTO usuario,
    Integer anoNascimento,
    TipoAnimalDTO tipoAnimal
) {
    public static PetResponseDTO valueOf(Pet pet) {
        Logger logger = Logger.getLogger(PetResponseDTO.class.getName());

        if (pet == null) {
            logger.log(Level.SEVERE, "Objeto Pet passado para valueOf é null.");
            return null;
        }

        Usuario usuario = pet.getUsuario();
        if (usuario == null) {
            logger.log(Level.WARNING, "Usuário associado a Pet é null.");
        }

        TipoAnimal tipoAnimal = pet.getTipoAnimal();
        if (tipoAnimal == null) {
            logger.log(Level.WARNING, "TipoAnimal associado a Pet é null.");
        }

        return new PetResponseDTO(
            pet.getId(),
            pet.getNome(),
            UsuarioResponseDTO.valueOf(usuario),
            pet.getAnoNascimento(),
            TipoAnimalDTO.valueOf(tipoAnimal)
        );
    }
}
