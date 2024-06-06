package br.projeto.petshop.dto;

import br.projeto.petshop.model.ItemCompra;
import br.projeto.petshop.model.Racao;

public record ItemCompraResponseDTO (
    String nome,
    double precoUnitario, 
    int quantidade,
    Racao racao
) {
    public static ItemCompraResponseDTO valueOf(ItemCompra itemCompra) {
        return new ItemCompraResponseDTO(
            itemCompra.getNome(),
            itemCompra.getPrecoUnitario(), 
            itemCompra.getQuantidade(),
            itemCompra.getRacao()
        );
    }
}
