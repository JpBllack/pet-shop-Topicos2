package br.projeto.petshop.dto;

import java.util.List;

import br.projeto.petshop.model.CartaoCredito;
import br.projeto.petshop.model.Endereco;
import br.projeto.petshop.model.ItemCompra;

public record ConcluirCompraDTO(
    List<ItemCompraDTO> itensCompra,
    EnderecoDTO endereco,
    CartaoCreditoDTO cartao
) {
    
}
