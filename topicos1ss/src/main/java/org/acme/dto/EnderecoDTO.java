package org.acme.dto;

import org.acme.model.Endereco;

import jakarta.validation.constraints.NotBlank;

public record EnderecoDTO(
        String logradouro,
        String numero,
        @NotBlank String complemento,
        String bairro,
        @NotBlank Long idCidade,
        @NotBlank String cep
) {
        public EnderecoDTO(Endereco e){
                this(e.getLogradouro(), e.getNumero(), e.getComplemento(), e.getBairro(), e.getCidade().getId(), e.getCep());
        }
}
