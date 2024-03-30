package br.projeto.petshop.dto;

import br.projeto.petshop.model.Perfil;
import br.projeto.petshop.model.Usuario;

public record UsuarioResponseDTO (
    Long id,
    String username,
    String email,
    String name,
    Perfil perfil
){
    public static UsuarioResponseDTO valueOf(Usuario user){
        return new UsuarioResponseDTO(user.getId(), user.getUsername(), user.getEmail(), user.getNome(), user.getPerfil());
    }
    
}
