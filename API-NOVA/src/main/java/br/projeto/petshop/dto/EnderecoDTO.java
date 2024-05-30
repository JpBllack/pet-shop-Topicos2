package br.projeto.petshop.dto;


import br.projeto.petshop.model.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoDTO(
        String logradouro,
        String numero,
        @NotBlank String complemento,
        String bairro,
        @NotNull Long idCidade,
        @NotBlank String cep,
        boolean isPrincipal  
) {
        public EnderecoDTO(Endereco e){
                this(e.getLogradouro(), e.getNumero(), e.getComplemento(), e.getBairro(), e.getMunicipio().getId(), e.getCep(),e.isPrincipal());
        }
}
