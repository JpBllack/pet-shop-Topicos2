package br.projeto.petshop.dto;

import br.projeto.petshop.model.Idade;
import br.projeto.petshop.model.Marca;
import br.projeto.petshop.model.Peso;
import br.projeto.petshop.model.Racao;
import br.projeto.petshop.model.TipoAnimal;

public record RacaoResponseDTO(
    Long id,
    String nome,
    String sabor,
    TipoAnimal animal,
    Peso peso,
    Idade idade,
    String imagem,
    Marca marca,
    Double preco,
    Double estoque
) {

    public static RacaoResponseDTO valueOf(Racao racao){
        return new RacaoResponseDTO(
            racao.getId(),
            racao.getNome(),
            racao.getSabor(),
             racao.getAnimal(), 
             racao.getPeso(), 
             racao.getIdade(), 
             racao.getImagem(), 
             racao.getMarca(),
             racao.getPreco(),
             racao.getEstoque());
    }
}
 