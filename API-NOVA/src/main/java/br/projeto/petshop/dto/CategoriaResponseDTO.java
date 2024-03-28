package br.projeto.petshop.dto;

import br.projeto.petshop.model.Categoria;

public record CategoriaResponseDTO(Long id, String nome) {
    public CategoriaResponseDTO(Categoria c){
        this(c.getId(), c.getNome());
    }
}
