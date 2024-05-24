package br.projeto.petshop.dto;

import br.projeto.petshop.model.Usuario;
import jakarta.validation.constraints.NotEmpty;

public record UsuarioBasicoResponseDTO(
        @NotEmpty(message = "O campo nome não pode ser nulo.") String nome,
        @NotEmpty(message = "O campo sobrenome não pode ser nulo.") String sobrenome,
        @NotEmpty(message = "O campo email não pode ser nulo.") String email,
        @NotEmpty(message = "O campo senha não pode ser nulo.") String senha) {

    public static UsuarioBasicoResponseDTO valueOf(Usuario user) {
        return new UsuarioBasicoResponseDTO(user.getNome(), user.getSobrenome(), user.getEmail(), user.getSenha());
    }
}
