package org.acme.dto;
import org.acme.model.Usuario;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public record UsuarioResponseDTO(
        String id, String nome, String email, String login, String cpf, List<String> perfis
) {
    public UsuarioResponseDTO(Usuario user) {
        this(user.getId(),
                user.getNome(),
                user.getEmail(),
                user.getLogin(),
                user.getCpf(),
                Objects.requireNonNullElse(
                user.getPerfis().stream().map(perfil -> perfil.getLabel()).collect(Collectors.toList()), null));

    }
}
