package org.acme.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;


public record VendaCompDTO(
    @NotBlank(message = "O campo 'idEndereco' não pode ser vazio!") Long idEndereco, String tipoPagamento, List<ItemVendaDTO> itensVendaDTO
) {
    
}
