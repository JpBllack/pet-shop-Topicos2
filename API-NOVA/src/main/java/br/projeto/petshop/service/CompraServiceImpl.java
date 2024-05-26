package br.projeto.petshop.service;

import br.projeto.petshop.dto.CompraResponseDTO;
import br.projeto.petshop.model.Compra;
import br.projeto.petshop.model.ItemCompra;
import br.projeto.petshop.repository.CompraRepository;
import br.projeto.petshop.resource.PetResource;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;

@ApplicationScoped
public class CompraServiceImpl implements CompraService {

    @Inject
    private CompraRepository compraRepository;

    private static final Logger LOG = Logger.getLogger(PetResource.class);

    @Override
    public List<CompraResponseDTO> getAllCompras() {
        return compraRepository.listAll().stream().map(e -> CompraResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<CompraResponseDTO> getComprasByUserId(Long userId) {
        List<Compra> compras = compraRepository.findByUserId(userId);
        return compras.stream().map(CompraResponseDTO::valueOf).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void concluirCompra(List<ItemCompra> itensCompra, Long userId) {

        if (itensCompra.isEmpty()) {
            throw new IllegalStateException("O carrinho está vazio");
        }

        Compra compra = new Compra();
        compra.setDataCompra(new Date());

        double precoTotal = calcularTotal(itensCompra);
        compra.setPrecoTotal(precoTotal);

        for (ItemCompra itemCompra : itensCompra) {
            compra.addItemCompra(itemCompra);
        }

        compra.setUsuarioId(userId);

        compraRepository.persist(compra);
    }

    private double calcularTotal(List<ItemCompra> itensCompra) {
        double total = 0.0;
        for (ItemCompra item : itensCompra) {
            total += item.getPrecoUnitario() * item.getQuantidade();
        }
        return total;
    }
}
