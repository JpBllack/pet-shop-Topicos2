package br.projeto.petshop.service;

import br.projeto.petshop.dto.RacaoDTO;
import br.projeto.petshop.dto.RacaoResponseDTO;

import java.util.List;

public interface RacaoService {

    public List<RacaoResponseDTO> getAll();

    public RacaoResponseDTO getById(long id);

    public List<RacaoResponseDTO> getByNome(String nome);

    public List<RacaoResponseDTO> getBySabor(String sabor);

    public RacaoResponseDTO insert(RacaoDTO racaoDTO);

    public RacaoResponseDTO update(long id, RacaoDTO racaoDTO);

    public void delete(long id);

    public RacaoResponseDTO changeImage(Long id, String ImageName);
}
