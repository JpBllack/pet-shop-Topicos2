package org.acme.dto;

import jakarta.validation.constraints.NotBlank;


public record ItemVendaDTO(
        @NotBlank(message = "o campo 'quantidade' tem que ser preenchido!")
        Integer quantidade,
        @NotBlank(message = "o campo 'idProduto' tem que ser preenchido!")
        Long idProduto
) {
}
