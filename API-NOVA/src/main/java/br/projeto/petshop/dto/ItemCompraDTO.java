package br.projeto.petshop.dto;

public record ItemCompraDTO(
    String nome,
    double precoUnitario,
    int quantidade,
    Long racao
) {
    
}
