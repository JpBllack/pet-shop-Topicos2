package br.projeto.petshop.service;

import br.projeto.petshop.dto.CartaoCreditoDTO;
import br.projeto.petshop.dto.CompraResponseDTO;
import br.projeto.petshop.dto.EnderecoDTO;
import br.projeto.petshop.dto.ItemCompraDTO;
import br.projeto.petshop.dto.MunicipioResponseDTO;
import br.projeto.petshop.dto.RacaoResponseDTO;
import br.projeto.petshop.model.CartaoCreditoHistorico;
import br.projeto.petshop.model.Compra;
import br.projeto.petshop.model.EnderecoHistorico;
import br.projeto.petshop.model.ItemCompra;
import br.projeto.petshop.model.Racao;
import br.projeto.petshop.model.Status;
import br.projeto.petshop.model.StatusCompra;
import br.projeto.petshop.repository.CompraRepository;
import br.projeto.petshop.repository.MunicipioRepository;
import br.projeto.petshop.repository.RacaoRepository;
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

    @Inject
    private RacaoRepository racaoRepository;

    @Inject
    private RacaoService racaoService;

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
    public void concluirCompra(List<ItemCompraDTO> itensCompraDTO, EnderecoDTO enderecoDTO,
            CartaoCreditoDTO cartaoDTO, Long userId) {

        if (itensCompraDTO.isEmpty()) {
            throw new IllegalStateException("O carrinho está vazio");
        }

        List<ItemCompra> itensCompra = itensCompraDTO.stream().map(dto -> {
            ItemCompra itemCompra = new ItemCompra();
            itemCompra.setNome(dto.nome());
            itemCompra.setPrecoUnitario(dto.precoUnitario());
            itemCompra.setQuantidade(dto.quantidade());
            
            Long racaoId = dto.racao();
            if (racaoId == null) {
                throw new IllegalStateException("Ração ID não pode ser nulo");
            }
    
            Racao racao = racaoRepository.findById(racaoId);
            if (racao == null) {
                throw new IllegalStateException("Ração não encontrada: " + racaoId);
            }
            itemCompra.setRacao(racao);
            return itemCompra;
        }).collect(Collectors.toList());

        verificarEstoque(itensCompra);

        Compra compra = new Compra();
        compra.setDataCompra(new Date());

        double precoTotal = calcularTotal(itensCompra);
        compra.setPrecoTotal(precoTotal);

        for (ItemCompra itemCompra : itensCompra) {
            compra.addItemCompra(itemCompra);
            atualizarEstoque(itemCompra.getRacao().getId(), itemCompra.getQuantidade()); // -> Atualização do estoque
        }

        compra.setUsuarioId(userId);

        StatusCompra primeiroStatus = new StatusCompra();
        primeiroStatus.setStatus(Status.valueOf(1));
        primeiroStatus.setStatus(Status.valueOf(2));
        primeiroStatus.setStatus(Status.valueOf(4));
        compra.addStatusCompra(primeiroStatus);

        compra.setEndereco(enderecoDTO.logradouro() + ", " + enderecoDTO.numero() + ", " + enderecoDTO.bairro() + ", "
                + enderecoDTO.complemento() + ", " + enderecoDTO.cep());

        compra.setCartao(cartaoDTO.numero());

        compraRepository.persist(compra);
    }

    private void verificarEstoque(List<ItemCompra> itensCompra) {
        for (ItemCompra itemCompra : itensCompra) {

            int estoqueDisponivel = obterEstoqueDisponivel(itemCompra);
            if (estoqueDisponivel < itemCompra.getQuantidade()) {
                throw new IllegalStateException("Estoque insuficiente para o item: " + itemCompra.getNome());
            }
        }
    }

    private int obterEstoqueDisponivel(ItemCompra itemCompra) {
        // Consulta ao banco de dados para obter o estoque disponível do item
        RacaoResponseDTO racaoResponseDTO = racaoService.getById(itemCompra.getRacao().getId());
        int estoqueItem = racaoResponseDTO.estoque();
        if (estoqueItem != 0) {
            return estoqueItem;
        } else {
            return 0; // Se não houver registro de estoque para o item, retorna 0
        }
    }

    @Transactional
    public void atualizarEstoque(Long id, int quantidadeVendida) {
        Racao racao = racaoRepository.findById(id);
        if (racao != null) {
            int novoEstoque = racao.getEstoque() - quantidadeVendida;
            if (novoEstoque < 0) {
                throw new IllegalStateException("Estoque insuficiente para a ração: " + racao.getNome());
            }
            racao.setEstoque(novoEstoque);
            racaoRepository.persist(racao);
        } else {
            throw new IllegalStateException("Ração não encontrada: " + id);
        }
    }

    private double calcularTotal(List<ItemCompra> itensCompra) {
        double total = 0.0;
        for (ItemCompra item : itensCompra) {
            total += item.getPrecoUnitario() * item.getQuantidade();
        }
        return total;
    }
}
