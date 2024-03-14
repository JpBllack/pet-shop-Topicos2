package org.acme.dto;

import org.acme.model.Venda;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public record VendaResponseCompDTO(
    Long id, Double valorTtotal, PagamentoResponseDTO pagamentoResponseDTO, List<ItemVendaResponseDTO> itens, LocalDateTime data
) {
    public VendaResponseCompDTO(Venda v){
        this(v.getId(), v.getValorTotal(), Objects.requireNonNullElse(new PagamentoResponseDTO(v.getPagamento()), null), Objects.requireNonNullElse(v.getItemVendaList().stream().map(ItemVendaResponseDTO::new).collect(Collectors.toList()), null), Objects.requireNonNullElse(v.getDataInclusao(), null));
    }
    
}
