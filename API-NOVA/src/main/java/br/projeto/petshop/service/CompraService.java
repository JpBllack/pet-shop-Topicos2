package br.projeto.petshop.service;

import java.util.List;

import br.projeto.petshop.dto.CartaoCreditoDTO;
import br.projeto.petshop.dto.CompraResponseDTO;
import br.projeto.petshop.dto.EnderecoDTO;
import br.projeto.petshop.model.ItemCompra;

public interface CompraService {

    public List<CompraResponseDTO> getAllCompras();

    public List<CompraResponseDTO> getComprasByUserId(Long userId);

    public List<ItemCompra> getItensCompraByCompraId(Long compraId);

    public void concluirCompra(List<ItemCompra> itensCompra, EnderecoDTO enderecoDTO, CartaoCreditoDTO cartaoDTO,  Long userId);

    
}