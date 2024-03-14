package org.acme.dto;

import org.acme.model.Telefone;

public record TelefoneResponseDTO(
        Long id, String codigoArea, String numero
) {
    public TelefoneResponseDTO(Telefone t){
        this(t.getId(), t.getCodigoArea(), t.getNumero());
    }
}
