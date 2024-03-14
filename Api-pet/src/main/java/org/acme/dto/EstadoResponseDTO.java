package org.acme.dto;

import org.acme.model.Estado;

public record EstadoResponseDTO(
    Long id,
    String nome,
    String sigla
) { 
    public EstadoResponseDTO (Estado estado){
        this(
            estado.getId(), //quando mudo o nome da variável não aceita, mas em cidadeResponse ele aceita
            estado.getnome(),
            estado.getsigla());
    }   
}
