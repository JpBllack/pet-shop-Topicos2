package br.projeto.petshop.dto;

import java.util.Date;

public record ConsultaDTO (
    Date data,
    String motivo,
    Long veterinario,
    Long pet
){
    
}

