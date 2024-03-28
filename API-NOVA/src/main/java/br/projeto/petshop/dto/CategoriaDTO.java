package br.projeto.petshop.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoriaDTO(@NotBlank String nome) {
}
