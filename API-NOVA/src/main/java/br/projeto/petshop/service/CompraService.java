package br.projeto.petshop.service;

import java.util.List;

import br.projeto.petshop.dto.CompraResponseDTO;
import br.projeto.petshop.model.ItemCompra;

public interface CompraService {

    public List<CompraResponseDTO> getAllCompras();

    public List<CompraResponseDTO> getComprasByUserId(Long userId);

    public void concluirCompra(List<ItemCompra> itensCompra, Long userId);

    
}