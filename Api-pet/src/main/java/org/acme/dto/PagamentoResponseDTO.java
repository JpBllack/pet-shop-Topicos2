package org.acme.dto;

import java.time.LocalDateTime;

import org.acme.model.Pagamento;

public record PagamentoResponseDTO(
        Long id,
        String tipo,
        Double valor,
        Boolean pago,
        LocalDateTime data
) {
    public PagamentoResponseDTO(Pagamento p){

        this(p.getId(), p.getTipo(), p.getValor(), p.getPago(), p.getDataInclusao());
    }
}
