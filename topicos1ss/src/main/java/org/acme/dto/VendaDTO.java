package org.acme.dto;

import jakarta.validation.constraints.NotBlank;


public record VendaDTO(
    @NotBlank(message = "O campo 'idEndereco' não pode ser vazio!") Long idEndereco, Long idPagamento
) {
    
}
