package br.projeto.petshop.dto;

import jakarta.validation.constraints.Pattern;

public record CpfDTO(
    @Pattern(regexp = "\\d{11}", message = "O CPF deve ter 11 dígitos")
    String cpf
) {
} 