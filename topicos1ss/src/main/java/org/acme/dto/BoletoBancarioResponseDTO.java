package org.acme.dto;

import org.acme.model.BoletoBancario;

public record BoletoBancarioResponseDTO(
        Long id, String banco, String numeroBoleto
) {
    public BoletoBancarioResponseDTO(BoletoBancario b){
        this(b.getId(),b.getBanco(), b.getNumeroBoleto());
    }
}
