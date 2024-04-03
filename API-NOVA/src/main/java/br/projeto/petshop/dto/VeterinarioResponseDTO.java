package br.projeto.petshop.dto;

import br.projeto.petshop.model.Usuario;

public record VeterinarioResponseDTO(
        Long id,
        String cpf,
        String nome,
        String email,
        String login
) {
    public static VeterinarioResponseDTO valueOf(Usuario veterinario) {
        return new VeterinarioResponseDTO(
                veterinario.getId(),
                veterinario.getCpf(),
                veterinario.getNome(),
                veterinario.getEmail(),
                veterinario.getUsername()
        );
    }

    public VeterinarioResponseDTO {
        // Construtor vazio necessário para o record
    }
}
