package br.projeto.petshop.dto;

import br.projeto.petshop.model.Usuario;

public record UsernameDTO(
    String username
) {
    public static UsernameDTO valueOf(Usuario user) {
        return new UsernameDTO(user.getUsername());
    }
}