package br.projeto.petshop.dto;

import br.projeto.petshop.model.Perfil;
import br.projeto.petshop.model.Veterinario;

public record VeterinarioResponseDTO(
        String nome,
        String email,
        String crvm,
        Perfil perfil
) {
    // Construtor que recebe um objeto Veterinario como argumento
    public VeterinarioResponseDTO(Veterinario veterinario) {
        this(veterinario.getNome(), veterinario.getEmail(), veterinario.getCrvm(), veterinario.getPerfil());
    }
}
