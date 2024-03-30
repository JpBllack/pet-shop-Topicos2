package br.projeto.petshop.dto;

import br.projeto.petshop.model.Usuario;

public record LoginResponseDTO (
    String email
) {
    public static LoginResponseDTO valueOf(Usuario user){
        return new LoginResponseDTO(user.getEmail());
    }
}