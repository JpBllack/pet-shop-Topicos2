package br.projeto.petshop.dto;

public record UpdateSenhaDTO(
    String senhaAtual,
    String novaSenha
) {
}