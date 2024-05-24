package br.projeto.petshop.dto;

import jakarta.validation.constraints.NotEmpty;

public record UsuarioBasicoDTO(
        @NotEmpty(message = "O campo nome não pode ser nulo.") String nome,
        @NotEmpty(message = "O campo sobrenome não pode ser nulo.") String sobrenome,
        @NotEmpty(message = "O campo email não pode ser nulo.") String email,
        @NotEmpty(message = "O campo senha não pode ser nulo.") String senha) {

}
