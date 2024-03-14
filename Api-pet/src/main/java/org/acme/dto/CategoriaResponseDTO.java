package org.acme.dto;

import org.acme.model.Categoria;

public record CategoriaResponseDTO(Long id, String nome) {
    public CategoriaResponseDTO(Categoria c){
        this(c.getId(), c.getNome());
    }
}
