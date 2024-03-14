package org.acme.dto;

import org.acme.model.Avaliacao;

public record AvaliacaoResponseDTO(
        Long id, Integer nota, String comentario
) {
    public AvaliacaoResponseDTO(Avaliacao a){
        this(a.getId(), a.getNota(), a.getComentario());
    }
}
