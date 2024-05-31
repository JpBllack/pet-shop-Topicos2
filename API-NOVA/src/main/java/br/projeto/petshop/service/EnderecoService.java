package br.projeto.petshop.service;

import jakarta.ws.rs.core.Response;


import java.util.List;

import br.projeto.petshop.dto.EnderecoDTO;
import br.projeto.petshop.dto.EnderecoResponseDTO;

public interface EnderecoService {

    public EnderecoResponseDTO setPrincipal(Long id); 
    
    public List<EnderecoResponseDTO> getAll();

    public List<EnderecoResponseDTO> getAllEnderecosByUser(Long id);

    public EnderecoResponseDTO getId(Long id);

    public EnderecoResponseDTO insert(EnderecoDTO enderecoDTO, Long userId);
    
    public EnderecoResponseDTO update(long id, EnderecoDTO enderecoDTO);


    public void delete(long id);
}
