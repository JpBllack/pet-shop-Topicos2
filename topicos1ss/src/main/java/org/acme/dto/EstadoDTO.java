package org.acme.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EstadoDTO(
        @NotBlank @Size(min = 4) String nome,
        @NotBlank @Size(min = 2, max = 3) String sigla
) {
}
