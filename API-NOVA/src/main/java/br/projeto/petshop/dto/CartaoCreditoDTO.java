package br.projeto.petshop.dto;

public record CartaoCreditoDTO(
    String numero,
    String codigoSeguranca,
    Integer mesValidade,
    Integer anoValidade,
    Integer usuario

) {
    
}
