package br.projeto.petshop.dto;

import br.projeto.petshop.model.Compra;
import br.projeto.petshop.model.StatusCompra;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public record CompraResponseDTO (
    Long id,
    List<ItemCompraResponseDTO> itemCompra,
    List<StatusCompraDTO> statusCompra,
    Double precoTotal,
    Date dataCompra 
){
    public static CompraResponseDTO valueOf(Compra compra){
        List<ItemCompraResponseDTO> itemCompraDTOs = compra.getItensCompra().stream()
                .map(ItemCompraResponseDTO::valueOf)
                .collect(Collectors.toList());

        List<StatusCompraDTO> statusCompraDTOs = compra.getStatusCompra().stream() // Convertendo StatusCompra para StatusCompraDTO
                .map(StatusCompraDTO::valueOf)
                .collect(Collectors.toList());

        return new CompraResponseDTO(compra.getId(), itemCompraDTOs, statusCompraDTOs, compra.getPrecoTotal(), compra.getDataCompra());
    }
}
