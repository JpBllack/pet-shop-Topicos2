package br.projeto.petshop.dto;

import br.projeto.petshop.model.Sexo;
import br.projeto.petshop.model.TipoAnimal;
import br.projeto.petshop.model.Usuario;

public record PetDTO (
    long id,
    String nome,
    Usuario usuario,
    Integer anoNascimento,
    TipoAnimal tipoAnimal
){

    
}
    