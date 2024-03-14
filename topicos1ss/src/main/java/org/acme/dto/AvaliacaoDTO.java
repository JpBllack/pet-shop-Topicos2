package org.acme.dto;

import jakarta.validation.constraints.NotBlank;

public record AvaliacaoDTO(
        @NotBlank Integer nota, String comentario
) {
}
