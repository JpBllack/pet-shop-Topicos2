package br.projeto.petshop.dto;


public record PetDTO(
    String nome,
    int anoNascimento,
    Long tipoAnimal,
    Long usuario
) {

    
}

