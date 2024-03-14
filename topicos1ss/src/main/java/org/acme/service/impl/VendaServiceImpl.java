package org.acme.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import org.acme.dto.*;
import org.acme.model.*;
import org.acme.repository.*;
import org.acme.service.VendaService;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@ApplicationScoped
public class VendaServiceImpl implements VendaService {

    public static final Logger LOG = Logger.getLogger(VendaServiceImpl.class);

    @Inject
    VendaRepository repository;

    @Inject
    JsonWebToken jsonWebToken;

    @Inject
    ItemVendaRepository itemVendaRepository;

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    ProdutoRepository produtoRepository;
    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    PagamentoRepository pagamentoRepository;

    @Inject
    AvaliacaoRepository avaliacaoRepository;

    @Override
    public Response getAll() {
        try {
            LOG.info("Requisição getAll()");
            List<VendaResponseCompDTO> v = new ArrayList<>();
            v = repository.findAll().stream()
                    .map(VendaResponseCompDTO::new)
                    .collect(Collectors.toList());
            return Response.ok(v).build();

        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição getAll()");
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Override
    public VendaResponseCompDTO getId(long id) {
        try {
            Venda v = repository.findById(id);
            Usuario u = usuarioRepository.findById(jsonWebToken.getSubject());
            if (!u.getPerfis().contains(Perfil.ADMIN)) {
                if (v.getEndereco().getUsuario() == u) {

                    return new VendaResponseCompDTO(v);
                } else {
                    throw new Exception("O usuario nao tem acesso a essa venda");
                }
            }
            LOG.info("Requisição getId()");
            return new VendaResponseCompDTO(v);

        } catch (Exception e) {

            LOG.info("erro ao rodar Requisição getId()");
            return null;
        }
    }

    @Transactional
    @Override
    public Response insert(VendaDTO dto) {

        try {
            LOG.info("Requisição insert()");

            Venda venda = new Venda();
            venda.setEndereco(enderecoRepository.findById(dto.idEndereco()));
            Pagamento p = pagamentoRepository.findById(dto.idPagamento());

            List<ItemVenda> list = new ArrayList<>();
            venda.setItemVendaList(list);
            repository.persist(venda);
            return Response.ok(venda.getId()).build();
        } catch (Exception e) {

            LOG.info("erro ao rodar Requisição insert()");
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @Transactional
    @Override
    public Response insertComp(VendaCompDTO dto) {

        try {
            LOG.info("Requisição insert()");

            Venda venda = new Venda();
            venda.setEndereco(enderecoRepository.findById(dto.idEndereco()));
            Pagamento p = new Pagamento();
            p.setPago(true);
            p.setTipo(dto.tipoPagamento());

            List<ItemVendaDTO> itemVendas = new ArrayList<>();

            List<ItemVenda> list = new ArrayList<>();

            if (dto.itensVendaDTO().size() == 1) {
                itemVendas.add(dto.itensVendaDTO().get(0));

                ItemVenda item = new ItemVenda();
                item.setQuantidade(dto.itensVendaDTO().get(0).quantidade());
                Produto pro = new Produto();

                pro = produtoRepository.findById(dto.itensVendaDTO().get(0).idProduto());
                item.setValorUnitario(pro.getValor());
                item.setValorTotal(dto.itensVendaDTO().get(0).quantidade() * item.getValorUnitario());

                itemVendaRepository.persist(item);

            } else if (dto.itensVendaDTO().size() > 1) {

                dto.itensVendaDTO().forEach(i -> itemVendas.add(i));

                venda.setValorTotal(0.0);
                itemVendas.forEach(i -> {

                    ItemVenda item = new ItemVenda();
                    item.setQuantidade(i.quantidade());
                    Produto pro = new Produto();

                    pro = produtoRepository.findById(i.idProduto());
                    item.setValorUnitario(pro.getValor());
                    item.setValorTotal(i.quantidade() * item.getValorUnitario());

                    itemVendaRepository.persist(item);
                    list.add(item);
                    venda.setValorTotal(venda.getValorTotal() + item.getValorTotal());
                });

            }
            p.setValor(venda.getValorTotal());
            pagamentoRepository.persist(p);
            venda.setPagamento(p);

            venda.setItemVendaList(list);

            repository.persist(venda);
            return Response.ok(new VendaResponseCompDTO(venda)).build();

        } catch (Exception e) {

            LOG.info("erro ao rodar Requisição insert()");
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Response insertItemVenda(VendaItemVendaDTO vendaDTO) {
        try {

            Venda v = repository.findById(vendaDTO.idVenda());

            ItemVenda itemVenda = new ItemVenda();
            itemVenda.setQuantidade(vendaDTO.dto().quantidade());
            itemVenda.setProduto(produtoRepository.findById(vendaDTO.dto().idProduto()));
            itemVenda.setValorUnitario(itemVenda.getProduto().getValor());
            itemVenda.setValorTotal(itemVenda.getValorUnitario() * vendaDTO.dto().quantidade());
            itemVendaRepository.persist(itemVenda);
            if (v.getValorTotal() != null) {
                v.setValorTotal(v.getValorTotal() + itemVenda.getValorTotal());
            } else {
                v.setValorTotal(itemVenda.getValorTotal());
            }
            if (!v.getItemVendaList().isEmpty()) {
                v.getItemVendaList().add(itemVenda);
            } else {
                List<ItemVenda> i = new ArrayList<>();
                i.add(itemVenda);
                v.setItemVendaList(i);
            }

            return Response.ok(new ItemVendaResponseDTO(itemVenda)).build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @Override
    @Transactional
    public Response insertItemVendaId(VendaItemVendaId vendaItemVendaId) {
        Venda v = repository.findById(vendaItemVendaId.idVenda());

        ItemVenda itemVenda = itemVendaRepository.findById(vendaItemVendaId.idItemVendaDTO());
        v.getItemVendaList().add(itemVenda);
        v.setValorTotal(v.getValorTotal() + itemVenda.getValorTotal());
        return Response.ok(new ItemVendaResponseDTO(itemVenda)).build();
    }

    @Override
    @Transactional
    public Response removeItemVendaId(VendaItemVendaId dto) {
        Venda v = repository.findById(dto.idVenda());

        ItemVenda itemVenda = itemVendaRepository.findById(dto.idItemVendaDTO());
        v.getItemVendaList().remove(itemVenda);
        v.setValorTotal(v.getValorTotal() - itemVenda.getValorTotal());
        return Response.ok(new ItemVendaResponseDTO(itemVenda)).build();
    }

    @Override
    @Transactional
    public Response adicionaAvaliacao(AvaliacaoProdutoVendaDTO dto) {
        AtomicBoolean ok = new AtomicBoolean(false);
        try {
            Venda v = repository.findById(dto.idVenda());

            Produto p = produtoRepository.findById(dto.idProduto());
            if (p.getNome().isEmpty()) {
                throw new Exception("Produto nao encontrado");
            }

            v.getItemVendaList().stream().forEach(i -> {
                if (i.getProduto() == p) {
                    ok.set(true);
                }
            });

            if (ok.get()) {

                Avaliacao a = new Avaliacao();
                a.setComentario(dto.dto().comentario());
                a.setNota(dto.dto().nota());
                avaliacaoRepository.persist(a);
                if (!p.getAvaliacaoList().isEmpty()) {

                    p.getAvaliacaoList().add(a);
                } else {
                    List<Avaliacao> ava = new ArrayList<>();
                    ava.add(a);
                    p.setAvaliacaoList(ava);
                }
                return Response.ok(new AvaliacaoResponseDTO(a)).build();
            } else {
                throw new Exception("Produto nao existe na compra");
            }

        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public Response insertPagamento(VendaPagamentoDTO vendaPagamentoDTO) {
        Venda v = repository.findById(vendaPagamentoDTO.idVenda());
        Pagamento p = pagamentoRepository.findById(vendaPagamentoDTO.idPagamento());
        v.setPagamento(p);
        return Response.ok(new VendaResponseDTO(v)).build();
    }

    @Override
    public Response adicionaAvaliacaoId(AvaliacaoProdutoVendaIdDTO dto) {
        try {
            Venda v = repository.findById(dto.idVenda());

            Produto p = produtoRepository.findById(dto.idProduto());

            if (v.getItemVendaList().contains(p)) {
                throw new Exception("Produto nao encotrado");
            }
            Avaliacao a = avaliacaoRepository.findById(dto.avaliacaoId());
            p.getAvaliacaoList().add(a);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @Transactional
    @Override
    public Response delete(long id) {
        try {
            LOG.info("Requisição delete()");
            Venda venda = repository.findById(id);
            if (venda != null) {
                repository.delete(venda);
                return Response.ok().build();
            } else {
                throw new Exception("venda não encontrado!");
            }
        } catch (Exception e) {

            LOG.info("erro ao rodar Requisição delete() - " + e.getMessage());
            return Response.notModified(e.getMessage()).build();
        }

    }
}
