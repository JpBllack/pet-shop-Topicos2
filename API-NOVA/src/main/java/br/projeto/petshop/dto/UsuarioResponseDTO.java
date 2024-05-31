package br.projeto.petshop.dto;

import br.projeto.petshop.model.Perfil;
import br.projeto.petshop.model.Usuario;

import java.util.logging.Level;
import java.util.logging.Logger;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String sobrenome,
        String cpf,
        String username,
        String email,
        Perfil perfil) {

    private static final Logger logger = Logger.getLogger(UsuarioResponseDTO.class.getName());

    public static UsuarioResponseDTO valueOf(Usuario user) {

        if (user == null) {
            throw new IllegalArgumentException("O objeto TipoAnimal não pode ser nulo");
        }
        return new UsuarioResponseDTO(
                user.getId(),
                user.getNome(),
                user.getSobrenome(),
                user.getCpf(),
                user.getUsername(),
                user.getEmail(),
                user.getPerfil());
    }
}
