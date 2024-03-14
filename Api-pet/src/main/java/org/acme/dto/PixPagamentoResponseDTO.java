package org.acme.dto;

import java.time.LocalDateTime;

import org.acme.model.PixPagamento;

public record PixPagamentoResponseDTO(
        Long id, String chave, Double valor,
        LocalDateTime data
) {
    public PixPagamentoResponseDTO(PixPagamento p){
        this(p.getId(), p.getChave(), p.getValor(), p.getDataInclusao());
    }
}
