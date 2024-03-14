package org.acme.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoriaDTO(@NotBlank String nome) {
}
