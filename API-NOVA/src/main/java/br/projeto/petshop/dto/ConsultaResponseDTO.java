package br.projeto.petshop.dto;

import java.util.Date;

import br.projeto.petshop.model.Consulta;
import br.projeto.petshop.model.Pet;
import br.projeto.petshop.model.Usuario;

public record ConsultaResponseDTO(
    Long id,
    Date data,
    String motivo,
    Usuario veterinario,
    Pet pet
) {
    public static ConsultaResponseDTO valueOf(Consulta consulta){
        return new ConsultaResponseDTO(consulta.getId(), consulta.getData(), consulta.getMotivo(), consulta.getVeterinario(), consulta.getPet());
    }
    
}
