package org.acme.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioUpdateLoginDTO(
        @NotBlank
        @Size(min = 3, message = "o campo 'login' tem que ter mais que 3 caracteres!")
        String login
) {
}
