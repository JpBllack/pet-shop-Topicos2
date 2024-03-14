package org.acme.dto;

import jakarta.validation.constraints.NotBlank;

public record PagamentoDTO(
        String tipo,
        @NotBlank(message = "O campo 'valor' não pode ser vazio") Double valor
) {
}
