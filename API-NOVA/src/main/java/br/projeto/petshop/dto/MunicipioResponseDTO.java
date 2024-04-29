package br.projeto.petshop.dto;

import br.projeto.petshop.model.Municipio;
import br.projeto.petshop.model.Estado;

public record MunicipioResponseDTO(Long id, String nome, EstadoResponseDTO estado) {
    // Construtor que aceita um objeto Municipio e inicializa o DTO com os valores dos campos
    public MunicipioResponseDTO(Municipio municipio) {
        this(municipio.getId(), municipio.getNome(), new EstadoResponseDTO(municipio.getEstadoId()));
    }
}
