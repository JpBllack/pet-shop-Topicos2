package org.acme.dto;

import org.acme.model.Endereco;

public record EnderecoResponseDTO(
        Long id,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        CidadeResponseDTO cidadeResponseDTO,
        String cep
) {
    public EnderecoResponseDTO(Endereco e){
        this(e.getId(), e.getLogradouro(), e.getNumero(), e.getComplemento(), e.getBairro(), new CidadeResponseDTO(e.getCidade()), e.getCep());
    }
}
