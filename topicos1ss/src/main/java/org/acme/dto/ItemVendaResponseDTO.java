package org.acme.dto;

import org.acme.model.ItemVenda;

public record ItemVendaResponseDTO(
        Long id,
        Integer quantidade,
        Double valorUnitario,
        Double valorTotal,
        Long idProduto
) {
    public ItemVendaResponseDTO(ItemVenda i){
        this(i.getId(), i.getQuantidade(), i.getValorUnitario(), i.getValorTotal(), i.getProduto().getId());
    }
}
