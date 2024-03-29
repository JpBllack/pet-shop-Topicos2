package br.projeto.petshop.dto;
import br.projeto.petshop.model.Perfil;
import br.projeto.petshop.model.Veterinario;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public record VeterinarioResponseDTO(
        String nome,
        String email,
        String cpf,
        Perfil perfil
) {
    public static VeterinarioResponseDTO valueof(Veterinario vet){
        return new VeterinarioResponseDTO(vet.getNome(), vet.getEmail(), vet.getCpf(), vet.getPerfil());
    }
    
}
