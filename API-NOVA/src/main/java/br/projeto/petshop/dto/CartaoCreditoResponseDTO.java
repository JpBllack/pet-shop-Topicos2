package br.projeto.petshop.dto;

import br.projeto.petshop.model.CartaoCredito;

public record CartaoCreditoResponseDTO(
    String numero,
    String usuario
) {
    public static CartaoCreditoResponseDTO valueOf(CartaoCredito cartaoCredito){
        String numeroCensurado = censurarNumeroCartao(cartaoCredito.getNumeroCartao());
        return new CartaoCreditoResponseDTO(numeroCensurado, cartaoCredito.getUsuario().getEmail());
    }

    public static String censurarNumeroCartao(String numeroCartao) {
        if (numeroCartao == null || numeroCartao.length() < 4) {
            return "****";
        }
        String ultimosDigitos = numeroCartao.substring(numeroCartao.length() - 4);
        return "**** **** **** " + ultimosDigitos;
    }
}
