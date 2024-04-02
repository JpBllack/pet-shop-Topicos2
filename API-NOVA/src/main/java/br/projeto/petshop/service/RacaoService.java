package br.projeto.petshop.service;

import br.projeto.petshop.dto.RacaoDTO;
import br.projeto.petshop.dto.RacaoResponseDTO;
import jakarta.ws.rs.core.Response;
import java.util.List;

public interface RacaoService {

    List<RacaoResponseDTO> getAll();

    RacaoResponseDTO getById(long id);

    List<RacaoResponseDTO> getBySabor(String sabor);

    Response update(long id, RacaoDTO racaoDTO);

    Response insert(RacaoDTO racaoDTO);

    Response delete(long id);
}
