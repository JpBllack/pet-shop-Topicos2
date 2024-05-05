package br.projeto.petshop.dto;


public record PetDTO(
    String nome,
    Long usuario,
    Integer anoNascimento,
    Long tipoAnimal
) {

    
}
