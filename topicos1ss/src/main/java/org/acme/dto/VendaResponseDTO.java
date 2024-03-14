package org.acme.dto;

import org.acme.model.Venda;

import java.time.LocalDateTime;

public record VendaResponseDTO(
    Long id, Double valorTtotal, Long idPagamento, LocalDateTime data
) {
    public VendaResponseDTO(Venda v){
        this(v.getId(), v.getValorTotal(), v.getPagamento().getId(), v.getDataInclusao());
    }
}
