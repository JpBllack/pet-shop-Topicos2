package br.projeto.petshop.dto;

import br.projeto.petshop.model.User;

public record LoginResponseDTO (
    String email
) {
    public static LoginResponseDTO valueOf(User user){
        return new LoginResponseDTO(user.getEmail());
    }
}