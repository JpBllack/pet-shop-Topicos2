package org.acme.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CidadeDTO(
    @NotBlank @Size(min = 2) String nome,
    @NotEmpty Long idEstado
) {
    
}
