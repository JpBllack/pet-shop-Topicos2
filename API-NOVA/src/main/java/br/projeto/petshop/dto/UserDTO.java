package br.projeto.petshop.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDTO (

    @Size(min = 3, max = 30, message = "O usuario deve possuir entre 3 e 30 caracteres")
    String username,

    @Email
    @NotBlank(message = "O email não pode estar em branco")
    String email,

    @NotBlank(message = "A senha não pode estar em branco")
    String password,
    
    @NotNull(message = "O perfil não pode ser nulo")
    Integer profile

){
    
}
