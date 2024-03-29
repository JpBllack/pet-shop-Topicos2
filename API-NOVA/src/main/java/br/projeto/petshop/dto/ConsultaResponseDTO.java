package br.projeto.petshop.dto;

import java.util.Date;

import br.projeto.petshop.model.Consulta;
import br.projeto.petshop.model.Pet;
import br.projeto.petshop.model.Usuario;
import br.projeto.petshop.model.Veterinario;

public record ConsultaResponseDTO(
    Date data,
    String motivo,
    Veterinario veterinario,
    Pet pet,
    Usuario usuario
) {
    public static ConsultaResponseDTO valueOf(Consulta consulta){
        return new ConsultaResponseDTO(consulta.getData(), consulta.getMotivo(), consulta.getVeterinario(), consulta.getPet(), consulta.getUsuario());
    }
    
}
