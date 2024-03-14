package org.acme.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioLogadoMudarSenhaDTO(
        String senhaAntiga,
        @NotBlank(message = "O campo 'senha' não pode estar em branco") @Size(min = 3, max = 1000) String novaSenha) {
}
