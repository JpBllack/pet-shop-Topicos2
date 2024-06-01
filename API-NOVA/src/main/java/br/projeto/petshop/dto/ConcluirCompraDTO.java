package br.projeto.petshop.dto;

import java.util.List;

import br.projeto.petshop.model.ItemCompra;

public record ConcluirCompraDTO(
    List<ItemCompra> itensCompra,
    EnderecoDTO endereco,
    CartaoCreditoDTO cartaoCredito
) {
    
}
