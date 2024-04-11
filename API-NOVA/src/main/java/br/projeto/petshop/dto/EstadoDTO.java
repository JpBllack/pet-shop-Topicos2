package br.projeto.petshop.dto;

import br.projeto.petshop.model.Estado;

public record EstadoDTO(String nome, String sigla) {
    public EstadoDTO(Estado estado) {
        this(estado.getNome(), estado.getSigla());
    }
    
    public static EstadoDTO valueOf(Estado estado) {
        return new EstadoDTO(estado);
    }
}
