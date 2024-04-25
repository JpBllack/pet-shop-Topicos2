package br.projeto.petshop.service;

import br.projeto.petshop.dto.RacaoDTO;
import java.util.List;

public interface RacaoService {

    List<RacaoDTO> getAll();

    RacaoDTO getById(long id);

    List<RacaoDTO> getBySabor(String sabor);

    void insert(RacaoDTO racaoDTO);

    void update(long id, RacaoDTO racaoDTO);

    void delete(long id);
}
