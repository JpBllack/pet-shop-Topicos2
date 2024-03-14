package org.acme.service;


import java.util.List;

import org.acme.dto.CidadeDTO;
import org.acme.dto.CidadeResponseDTO;

import jakarta.ws.rs.core.Response;

public interface CidadeService {

    public Response insert(CidadeDTO cidade);

    public CidadeResponseDTO update(CidadeDTO cidadeDTO, Long id);

    public Response delete(Long id);

    public CidadeResponseDTO findById(Long id);

    public List<CidadeResponseDTO> findByNome(String nome);

    public List<CidadeResponseDTO> findAll(); 
}
