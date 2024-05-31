package br.projeto.petshop.dto;

import br.projeto.petshop.model.Estado;

public record EstadoResponseDTO(Long id, String nome, String sigla) {
    public static EstadoResponseDTO valueOf(Estado estado) {
        return new EstadoResponseDTO(estado.getId(), estado.getNome(), estado.getSigla());
    }
}
