package br.projeto.petshop.dto;

import br.projeto.petshop.model.Endereco;

public record EnderecoResponseDTO(
        Long id,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        MunicipioResponseDTO municipioResponseDTO,
        String cep
) {
    public EnderecoResponseDTO(Endereco e){
        this(e.getId(), e.getLogradouro(), e.getNumero(), e.getComplemento(), e.getBairro(), new MunicipioResponseDTO(e.getMunicipio()), e.getCep());
    }
}
