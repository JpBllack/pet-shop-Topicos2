package br.projeto.petshop.service;

import java.util.List;

import br.projeto.petshop.dto.CartaoCreditoDTO;
import br.projeto.petshop.dto.CartaoCreditoResponseDTO;

public interface CartaoCreditoService {

    public List<CartaoCreditoResponseDTO> getAllByUser(Long id);

    public CartaoCreditoResponseDTO getById(Long id);

    public CartaoCreditoResponseDTO insert(CartaoCreditoDTO dto, Long idUser);

    public void delete(Long id);

    
}
