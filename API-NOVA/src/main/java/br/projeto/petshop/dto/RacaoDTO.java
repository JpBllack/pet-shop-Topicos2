package br.projeto.petshop.dto;

import br.projeto.petshop.model.Peso;
import br.projeto.petshop.model.Idade;
import br.projeto.petshop.model.TipoAnimal;

public record RacaoDTO(
    long id,
    String sabor,
    TipoAnimal animal,
    Peso peso,
    Idade idade
) {}
