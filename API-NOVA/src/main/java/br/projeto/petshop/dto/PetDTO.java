package br.projeto.petshop.dto;

import br.projeto.petshop.model.Pet;
import br.projeto.petshop.model.Usuario;
import br.projeto.petshop.model.TipoAnimal;

public record PetDTO(
    String nome,
    Long usuario,
    Integer anoNascimento,
    Long tipoAnimal
) {

    
}
