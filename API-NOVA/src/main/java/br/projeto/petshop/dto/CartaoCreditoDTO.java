package br.projeto.petshop.dto;

public record CartaoCreditoDTO(
    String nome,
    String numero,
    String codigoSeguranca,
    Integer mesValidade,
    Integer anoValidade,
    boolean isPrincipal

) {
    
}
