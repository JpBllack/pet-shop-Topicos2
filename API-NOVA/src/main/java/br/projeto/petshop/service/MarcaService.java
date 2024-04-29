package br.projeto.petshop.service;

import java.util.List;

import br.projeto.petshop.dto.MarcaDTO;
import br.projeto.petshop.dto.MarcaResponseDTO;

public interface MarcaService {
    
    public List<MarcaResponseDTO> getAll();
    
    public MarcaResponseDTO insert(MarcaDTO dto);

    public MarcaResponseDTO update(Long id, MarcaDTO dto);

    public void delete(Long id);

    public MarcaResponseDTO getByNome(String nome);

    public MarcaResponseDTO getById(Long id);
}
