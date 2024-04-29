package br.projeto.petshop.dto;

import br.projeto.petshop.model.Peso;
import br.projeto.petshop.model.Racao;
import br.projeto.petshop.model.Idade;

public record RacaoDTO(
    long id,
    String sabor,
    TipoAnimalDTO animal,
    Peso peso,
    Idade idade,
    String imagem
) {
    public RacaoDTO {
        // Construtor vazio necessário para que o record funcione
    }

    public static RacaoDTO valueOf(Racao racao) {
        return new RacaoDTO(
            racao.getId(),
            racao.getSabor(),
            TipoAnimalDTO.valueOf(racao.getAnimal()),
            racao.getPeso(),
            racao.getIdade(),
            racao.getImagem()
        );
    }
}
