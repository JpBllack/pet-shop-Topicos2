package br.projeto.petshop.dto;

import br.projeto.petshop.model.Estado;

public record EstadoDTO(long id,String nome, String sigla) {
    public EstadoDTO(Estado estado) {
        this(estado.getId(), estado.getNome(), estado.getSigla());
    }
    
    public static EstadoDTO valueOf(Estado estado) {
        return new EstadoDTO(estado);
    }
}
