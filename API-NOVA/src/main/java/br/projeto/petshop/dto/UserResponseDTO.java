package br.projeto.petshop.dto;

import br.projeto.petshop.model.Profile;
import br.projeto.petshop.model.User;

public record UserResponseDTO (
    Long id,
    String username,
    String email,
    String name,
    Profile profile
){
    public static UserResponseDTO valueOf(User user){
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail(), user.getName(), user.getProfile());
    }
    
}
