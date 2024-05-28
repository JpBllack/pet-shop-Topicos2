package br.projeto.petshop.service;

import jakarta.ws.rs.core.Response;


import java.util.List;

import br.projeto.petshop.dto.EnderecoDTO;
import br.projeto.petshop.dto.EnderecoResponseDTO;

public interface EnderecoService {

    List<EnderecoResponseDTO> getAll();

    EnderecoResponseDTO getId(Long id);

    Response insert(EnderecoDTO enderecoDTO, Long userId);
    
    Response update(long id, EnderecoDTO enderecoDTO);

    Response delete(long id);
}
