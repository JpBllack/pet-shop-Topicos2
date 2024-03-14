package org.acme.dto;

import org.acme.model.Produto;

import java.util.List;
import java.util.stream.Collectors;

public record ProdutoResponseDTO(
        Long id,
        String nome,
        String descricao,
        Double valor,
        Integer quantEstoque,
        CategoriaResponseDTO c,
        List<AvaliacaoResponseDTO> avaliacao) {
    public ProdutoResponseDTO(Produto p){
        this(p.getId(), p.getNome(), p.getDescricao(), p.getValor(), p.getQuantEstoque(), new CategoriaResponseDTO(p.getCategoria()), p.getAvaliacaoList().stream().map(AvaliacaoResponseDTO::new).collect(Collectors.toList()) );
    }
}
