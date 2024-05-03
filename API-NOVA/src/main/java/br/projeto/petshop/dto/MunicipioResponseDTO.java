package br.projeto.petshop.dto;


import br.projeto.petshop.model.Municipio;

public record MunicipioResponseDTO(long id, String nome, EstadoResponseDTO estado) {
    public MunicipioResponseDTO(Municipio municipio) {
        this(
            municipio.getId(),
            municipio.getNome(),
            new EstadoResponseDTO(municipio.getEstadoId())  // Using EstadoResponseDTO constructor
        );
    }
}
