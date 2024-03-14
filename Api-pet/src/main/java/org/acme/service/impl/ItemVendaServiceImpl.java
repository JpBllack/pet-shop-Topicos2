package org.acme.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import org.acme.dto.ItemVendaDTO;
import org.acme.dto.ItemVendaResponseDTO;
import org.acme.model.ItemVenda;
import org.acme.model.Produto;
import org.acme.repository.ItemVendaRepository;
import org.acme.repository.ProdutoRepository;
import org.acme.service.ItemVendaService;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ItemVendaServiceImpl implements ItemVendaService {

    public static final Logger LOG = Logger.getLogger(ItemVendaServiceImpl.class);

    @Inject
    ItemVendaRepository repository;

    @Inject
    ProdutoRepository produtoRepository;

    @Override
    public List<ItemVendaResponseDTO> getAll() {
        try {
            LOG.info("Requisição getAll()");

            return repository.findAll().stream()
                    .map(ItemVendaResponseDTO::new)
                    .collect(Collectors.toList());
        }catch (Exception e){
            LOG.error("Erro ao rodar Requisição getAll()");
            return null;
        }
    }

    @Override
    public ItemVendaResponseDTO getId(long id) {
        try {
            LOG.info("Requisição getId()");

            return new ItemVendaResponseDTO(repository.findById(id));
        }catch (Exception e){

            LOG.info("erro ao rodar Requisição getId()");
            return null;
        }
    }

    @Transactional
    @Override
    public Response insert(ItemVendaDTO dto) {

        try {
            LOG.info("Requisição insert()");

            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setQuantidade(dto.quantidade());
            Produto p = produtoRepository.findById(dto.idProduto());
            itemVenda.setProduto(p);
            itemVenda.setValorUnitario(itemVenda.getProduto().getValor());
            itemVenda.setValorTotal(itemVenda.getValorUnitario() * dto.quantidade());
            repository.persist(itemVenda);
            return Response.ok(new ItemVendaResponseDTO(itemVenda)).build();

        }catch (Exception e){

            LOG.info("erro ao rodar Requisição insert()");
            return Response.status(Status.BAD_GATEWAY).entity(e.getMessage()).build();
        }
    }

    @Transactional
    @Override
    public Response delete(long id) {
        try {
            LOG.info("Requisição delete()");
            ItemVenda itemVenda = repository.findById(id);
            if (itemVenda != null) {
                repository.delete(itemVenda);
                return Response.ok().build();
            } else {
                throw new Exception("itemVenda não encontrado!");
            }
        } catch (Exception e) {

            LOG.info("erro ao rodar Requisição delete() - " + e.getMessage());
            return Response.notModified(e.getMessage()).build();

        }
    }
}
