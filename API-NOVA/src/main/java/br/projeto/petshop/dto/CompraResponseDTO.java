package br.projeto.petshop.dto;

import br.projeto.petshop.model.Compra;

import java.util.List;
import java.util.stream.Collectors;

public record CompraResponseDTO (
    Long id,
    List<ItemCompraResponseDTO> itemCompra,
    Double precoTotal
){
    public static CompraResponseDTO valueOf(Compra compra){
        List<ItemCompraResponseDTO> itemCompraDTOs = compra.getItensCompra().stream()
                .map(ItemCompraResponseDTO::valueOf)
                .collect(Collectors.toList());

        return new CompraResponseDTO(compra.getId(), itemCompraDTOs, compra.getPrecoTotal());
    }
}
