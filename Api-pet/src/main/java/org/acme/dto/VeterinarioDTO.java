package org.acme.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record VeterinarioDTO(

        @CPF
                String cpf,
        @NotBlank(message = "O campo 'nome' não pode estar em branco")
        @Size(max = 100, message = "O campo 'nome' deve ter no máximo 100 caracteres")
        @Size(min = 3, message = "O campo 'nome' deve ter no minimo 3 caracteres")
                String nome,
        @Email
        @NotBlank(message = "O campo 'email' não pode estar em branco")
        @Size(max = 100, message = "O campo 'login' deve ter no máximo 100 caracteres")
                String email,

        @NotBlank(message = "O campo 'login' não pode estar em branco")
        @Size(max = 50, message = "O campo 'login' deve ter no máximo 50 caracteres") String login,
        @NotBlank(message = "O campo 'senha' não pode estar em branco") @Size(min = 3, max = 1000) String senha) {

}