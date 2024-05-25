package br.projeto.petshop.service;

import java.util.List;

import br.projeto.petshop.model.ItemCompra;

public interface CompraService {

    public void concluirCompra(List<ItemCompra> itensCompra);

    
}