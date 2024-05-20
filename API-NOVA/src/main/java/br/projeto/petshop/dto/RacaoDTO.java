package br.projeto.petshop.dto;

import br.projeto.petshop.model.Peso;
import br.projeto.petshop.model.Racao;
import br.projeto.petshop.model.Idade;

public record RacaoDTO(
    String sabor,
    Long animal,
    Peso peso,
    Idade idade,
    String imagem,
    Long marca,
    Double preco
) {
    
}
