package br.projeto.petshop.dto;

import br.projeto.petshop.model.Marca;

public record MarcaResponseDTO(
    Long id,
    String nome
) {

    public static MarcaResponseDTO valueOf(Marca marca){
        return new MarcaResponseDTO(marca.getId(), marca.getNome());
    }

} 
