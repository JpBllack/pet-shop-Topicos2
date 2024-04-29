package br.projeto.petshop.dto;

import br.projeto.petshop.model.Municipio;
import br.projeto.petshop.model.Estado;

public record MunicipioDTO(long id, String nome, EstadoDTO estado) {
    
    // Construtor que aceita um objeto Municipio e inicializa o DTO com os valores dos campos
    public MunicipioDTO(Municipio municipio) {
        this(municipio.getId(), municipio.getNome(), EstadoDTO.valueOf(municipio.getEstadoId()));
    }

    // Converte a entidade Municipio para MunicipioDTO
    public static MunicipioDTO valueOf(Municipio municipio) {
        return new MunicipioDTO(municipio);
    }


}
