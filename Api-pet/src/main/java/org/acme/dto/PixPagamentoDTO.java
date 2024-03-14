package org.acme.dto;

import jakarta.validation.constraints.NotBlank;

public record PixPagamentoDTO(
        String chave, @NotBlank(message = "O campo 'valor' não pode ser vazio") Double valor
) {
}
