package br.projeto.petshop.dto;

import br.projeto.petshop.model.Endereco;

public record EnderecoResponseDTO(
        Long id,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String municipioResponseDTO,
        String cep,
        boolean isPrincipal
) {
    public static EnderecoResponseDTO valueOf(Endereco endereco){
        return new EnderecoResponseDTO(
         endereco.getId(),
         endereco.getLogradouro(),
         endereco.getNumero(), 
         endereco.getComplemento(), 
         endereco.getBairro(), 
         endereco.getMunicipio().getNome(), 
         endereco.getCep(), 
         endereco.isPrincipal()
         );  
    }
}
