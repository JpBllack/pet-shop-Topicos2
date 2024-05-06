package br.projeto.petshop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailDTO(
    @NotBlank
    @Email
    String email
) {
    
}