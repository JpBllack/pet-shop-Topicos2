package br.projeto.petshop.dto;

import br.projeto.petshop.model.ItemCompra;

public record ItemCompraResponseDTO (
    String nome,
    double preco, 
    int quantidade
) {
    public static ItemCompraResponseDTO valueOf(ItemCompra itemCompra) {
        return new ItemCompraResponseDTO(
            itemCompra.getNome(),
            itemCompra.getPrecoUnitario(), 
            itemCompra.getQuantidade()
        );
    }
}
