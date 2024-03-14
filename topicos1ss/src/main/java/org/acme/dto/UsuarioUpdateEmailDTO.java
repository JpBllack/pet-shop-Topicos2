package org.acme.dto;

import jakarta.validation.constraints.Email;

public record UsuarioUpdateEmailDTO(

                @Email
        String email
) {
}
