package org.acme.dto;
import org.acme.model.Veterinario;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public record VeterinarioResponseDTO(
        String id, String nome, String email,String cpf, List<String> perfis
) {
    public VeterinarioResponseDTO(Veterinario vet) {
        this(vet.getId(),
             vet.getNome(),
             vet.getEmail(),      
             vet.getCpf(),
             Objects.requireNonNullElse(
             vet.getPerfis().stream().map(perfil -> perfil.getLabel()).collect(Collectors.toList()), null));

    }
}
