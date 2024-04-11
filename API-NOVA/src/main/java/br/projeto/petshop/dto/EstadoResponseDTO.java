package br.projeto.petshop.dto;

import br.projeto.petshop.model.Estado;

public record EstadoResponseDTO(Long id, String nome, String sigla) {
    public EstadoResponseDTO(Estado estado) {
        this(estado.getId(), estado.getNome(), estado.getSigla());
    }
}
