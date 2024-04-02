package br.projeto.petshop.dto;

import br.projeto.petshop.model.Perfil;
import br.projeto.petshop.model.Usuario;

public record UsuarioResponseDTO (
    Long id,
    String nome,
    String cpf,
    String username,
    String email,
    Perfil perfil
){
    public static UsuarioResponseDTO valueOf(Usuario user){
        return new UsuarioResponseDTO(user.getId(), user.getNome(), user.getCpf(), user.getUsername(), user.getEmail(), user.getPerfil());
    }
}
