package org.acme.dto;

import jakarta.validation.constraints.NotBlank;

public record CartaoCreditoDTO(
        @NotBlank String numeroCartao,@NotBlank String dataValidade, String cvv, String bandeiraCartao, @NotBlank Double valor
) {
}
