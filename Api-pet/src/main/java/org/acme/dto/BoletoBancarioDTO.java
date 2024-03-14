package org.acme.dto;

import jakarta.validation.constraints.NotBlank;

public record BoletoBancarioDTO(
        @NotBlank String banco, String numeroBoleto, @NotBlank(message = "O campo 'valor' não pode ser vazio")  Double valor
) {
}
