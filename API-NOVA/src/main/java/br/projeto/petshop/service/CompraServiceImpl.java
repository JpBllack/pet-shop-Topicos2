package br.projeto.petshop.service;

import br.projeto.petshop.dto.CartaoCreditoDTO;
import br.projeto.petshop.dto.CompraResponseDTO;
import br.projeto.petshop.dto.EnderecoDTO;
import br.projeto.petshop.dto.MunicipioResponseDTO;
import br.projeto.petshop.model.CartaoCreditoHistorico;
import br.projeto.petshop.model.Compra;
import br.projeto.petshop.model.EnderecoHistorico;
import br.projeto.petshop.model.ItemCompra;
import br.projeto.petshop.model.Status;
import br.projeto.petshop.model.StatusCompra;
import br.projeto.petshop.repository.CompraRepository;
import br.projeto.petshop.repository.MunicipioRepository;
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

    @Inject
    private MunicipioRepository municipioRepository;

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
    public List<ItemCompra> getItensCompraByCompraId(Long compraId) {
        return compraRepository.findItensByCompraId(compraId);
    }

    @Override
    @Transactional
    public void concluirCompra(List<ItemCompra> itensCompra, EnderecoDTO enderecoDTO,
            CartaoCreditoDTO cartaoDTO, Long userId) {

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

        StatusCompra primeiroStatus = new StatusCompra();
        primeiroStatus.setStatus(Status.valueOf(1)); // Defina o status inicial aqui
        compra.addStatusCompra(primeiroStatus);

        compra.setEndereco(enderecoDTO.logradouro() + ", " + enderecoDTO.numero() + ", " + enderecoDTO.bairro() + ", " + enderecoDTO.complemento() + ", "+ enderecoDTO.cep());

        compra.setCartao(cartaoDTO.numero());

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
