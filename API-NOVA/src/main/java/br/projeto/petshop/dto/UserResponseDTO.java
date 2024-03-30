package br.projeto.petshop.dto;

import br.projeto.petshop.model.Perfil;
import br.projeto.petshop.model.Usuario;

public record UserResponseDTO (
    Long id,
    String username,
    String email,
    String name,
    Perfil perfil
){
    public static UserResponseDTO valueOf(Usuario user){
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail(), user.getNome(), user.getPerfil());
    }
    
}
