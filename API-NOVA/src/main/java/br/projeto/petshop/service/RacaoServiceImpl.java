package br.projeto.petshop.service;

import br.projeto.petshop.dto.RacaoDTO;
import br.projeto.petshop.dto.RacaoResponseDTO;
import br.projeto.petshop.model.Racao;
import br.projeto.petshop.repository.RacaoRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

public class RacaoServiceImpl implements RacaoService {

    @Inject
    RacaoRepository racaoRepository;

    @Override
    public List<RacaoResponseDTO> getAll() {
        return racaoRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Response insert(RacaoDTO racaoDTO) {
        Racao racao = new Racao();
        racao.setId(racaoDTO.id());
        racao.setSabor(racaoDTO.sabor());
        racao.setAnimal(racaoDTO.animal());
        racao.setPeso(racaoDTO.peso());
        racao.setIdade(racaoDTO.idade());
        racaoRepository.persist(racao);
        return Response.status(Response.Status.CREATED).build();
    }

   

    @Override
    public RacaoResponseDTO getById(long id) {
        Racao racao = racaoRepository.findById(id);
        if (racao != null) {
            return convertToResponseDTO(racao);
        }
        return null;
    }

    @Override
    public List<RacaoResponseDTO> getBySabor(String sabor) {
        return racaoRepository.findBySabor(sabor).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    @Override
    public Response delete(long id) {
        Racao racao = racaoRepository.findById(id);
        if (racao != null) {
            racaoRepository.delete(racao);
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    private RacaoResponseDTO convertToResponseDTO(Racao racao) {
        return new RacaoResponseDTO(racao.getId(), racao.getSabor(), racao.getAnimal().getNome());
    }

    @Override
public Response update(long id, RacaoDTO racaoDTO) {
    Racao racao = racaoRepository.findById(id);
    if (racao != null) {
        racao.setSabor(racaoDTO.sabor());
        racao.setAnimal(racaoDTO.animal());
        racao.setPeso(racaoDTO.peso());
        racao.setIdade(racaoDTO.idade());
        
        racaoRepository.persist(racao);
        return Response.ok().build();
    } else {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}

}
