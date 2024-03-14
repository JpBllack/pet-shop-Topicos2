package org.acme.dto;

public record ProdutoDTO(
        String nome,
        String descricao,
        Double valor,
        Integer quantEstoque,
        Long idCategoria
) {
}
