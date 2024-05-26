package br.projeto.petshop.dto;

import java.util.List;

import br.projeto.petshop.model.Compra;
import br.projeto.petshop.model.ItemCompra;

public record CompraResponseDTO (
    List<ItemCompra> itemCompra,
    Double precoTotal
){
    public static CompraResponseDTO valueOf(Compra compra){
        return new CompraResponseDTO(compra.getItensCompra(), compra.getPrecoTotal());
    }
    
}
