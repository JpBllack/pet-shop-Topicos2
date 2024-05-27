package br.projeto.petshop.dto;

import br.projeto.petshop.model.StatusCompra;

public record StatusCompraDTO (
    Long id,
    String status,
    String data
){
    public static StatusCompraDTO valueOf(StatusCompra statusCompra) {
        return new StatusCompraDTO(
            statusCompra.getId(),
            statusCompra.getStatus().getLabel(), // Obtendo o label do enum Status
            statusCompra.getData().toString() // Convertendo a data para String (ajuste conforme necessário)
        );
    }
}
