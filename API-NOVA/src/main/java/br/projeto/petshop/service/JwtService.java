package br.projeto.petshop.service;

import br.projeto.petshop.dto.UserResponseDTO;

public interface JwtService {
    public String generateJwt(UserResponseDTO dto);
}
