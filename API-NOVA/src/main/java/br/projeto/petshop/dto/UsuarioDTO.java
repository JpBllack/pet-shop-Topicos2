package br.projeto.petshop.dto;

import br.projeto.petshop.model.Perfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioDTO (
    @NotBlank(message = "O nome não pode estar em branco")
    String nome,  
  
    String cpf,

    @Size(min = 3, max = 30, message = "O usuário deve possuir entre 3 e 30 caracteres")
    String username,

    @Email
    @NotBlank(message = "O email não pode estar em branco")
    String email,

    @NotBlank(message = "A senha não pode estar em branco")
    String senha,
    
    
    @NotNull(message = "O perfil não pode ser nulo")
    Perfil perfil
){
    
}
