package org.acme.dto;

import org.hibernate.validator.constraints.br.CPF;

public record UsuarioUpdateCpfDTO(
        @CPF String cpf
) {
}
