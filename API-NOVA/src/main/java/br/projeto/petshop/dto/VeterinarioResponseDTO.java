package br.projeto.petshop.dto;

import br.projeto.petshop.model.Veterinario;

public record VeterinarioResponseDTO(
        Long id,
        String cpf,
        String nome,
        String email,
        String login,
        String crvm
) {
    public static VeterinarioResponseDTO valueOf(Veterinario veterinario) {
        return new VeterinarioResponseDTO(
                veterinario.getId(),
                veterinario.getCpf(),
                veterinario.getNome(),
                veterinario.getEmail(),
                veterinario.getUsername(),
                veterinario.getCrvm()
        );
    }

    public VeterinarioResponseDTO {
        // Construtor vazio necessário para o record
    }
}
