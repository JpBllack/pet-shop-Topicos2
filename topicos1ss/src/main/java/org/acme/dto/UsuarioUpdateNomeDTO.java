package org.acme.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioUpdateNomeDTO(
        @NotBlank
        @Size(min = 3, message = "O campo 'nome' deve ter no minimo 3 caracteres")
        @Size(max = 100, message = "O campo 'nome' deve ter no maximo 100 caracteres")
        String nome
) {
}
