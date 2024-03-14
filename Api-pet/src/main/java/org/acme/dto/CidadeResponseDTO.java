package org.acme.dto;

import org.acme.model.Cidade;

public record CidadeResponseDTO(
    Long id,
    String nome,
    EstadoResponseDTO estadoResponseDTO
) 

{
   public CidadeResponseDTO (Cidade cidade){
    this(cidade.getId(), 
    cidade.getNome(), 
    new EstadoResponseDTO(cidade.getEstado())
    );
   }
}
