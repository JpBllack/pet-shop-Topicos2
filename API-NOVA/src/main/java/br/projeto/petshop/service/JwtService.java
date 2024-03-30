package br.projeto.petshop.service;

import br.projeto.petshop.dto.UsuarioResponseDTO;

public interface JwtService {
    public String generateJwt(UsuarioResponseDTO dto);
}
