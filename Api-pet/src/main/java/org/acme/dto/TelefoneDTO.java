package org.acme.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TelefoneDTO(
        @Size(min = 2, max = 3) @NotBlank String codigoArea,
        @Size(min = 6, max = 25) @NotBlank String numero
) {
}
