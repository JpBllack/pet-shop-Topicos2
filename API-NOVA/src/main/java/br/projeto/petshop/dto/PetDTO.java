package br.projeto.petshop.dto;

import br.projeto.petshop.model.Sexo;
import br.projeto.petshop.model.TipoAnimal;

public record PetDTO (
    String nome,
    Integer anoNascimentoInteger,
    TipoAnimal tipoAnimal,
    Sexo sexo
){

    
}
