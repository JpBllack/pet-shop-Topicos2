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
        if (racao == null) {
            // Você pode lançar uma exceção específica ou retornar um valor padrão aqui
            throw new IllegalArgumentException("O objeto racao não pode ser nulo");
        }
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
