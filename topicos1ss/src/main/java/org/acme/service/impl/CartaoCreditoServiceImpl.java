package org.acme.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import org.acme.dto.AvaliacaoResponseDTO;
import org.acme.dto.CartaoCreditoDTO;
import org.acme.dto.CartaoCreditoResponseDTO;
import org.acme.model.BoletoBancario;
import org.acme.model.CartaoCredito;
import org.acme.repository.CartaoCreditoRepository;
import org.acme.service.CartaoCreditoService;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CartaoCreditoServiceImpl implements CartaoCreditoService {
    public static final Logger LOG = Logger.getLogger(CartaoCreditoServiceImpl.class);
    @Inject
    CartaoCreditoRepository repository;

    @Override
    public List<CartaoCreditoResponseDTO> getAll() {
        try {
            LOG.info("Requisição getAll()");

            return repository.findAll().stream()
                    .map(CartaoCreditoResponseDTO::new)
                    .collect(Collectors.toList());
        }catch (Exception e){
            LOG.error("Erro ao rodar Requisição getAll()");
            return null;
        }
    }

    @Override
    public CartaoCreditoResponseDTO getId(long id) {
        try {
            LOG.info("Requisição getId()");
            return new CartaoCreditoResponseDTO(repository.findById(id));

        }catch (Exception e){

            LOG.info("erro ao rodar Requisição getId()");
            return null;
        }
    }

    @Transactional
    @Override
    public Response insert(CartaoCreditoDTO dto) {

        try {
            LOG.info("Requisição insert()");

            CartaoCredito cartaoCredito = new CartaoCredito();
            cartaoCredito.setNumeroCartao(dto.numeroCartao());
            cartaoCredito.setDataValidade(dto.dataValidade());
            cartaoCredito.setCvv(dto.cvv());
            cartaoCredito.setBandeiraCartao(dto.bandeiraCartao());
            cartaoCredito.setTipo("cartao credito");
            cartaoCredito.setValor(dto.valor());
            repository.persist(cartaoCredito);
            return Response.ok(new CartaoCreditoResponseDTO(cartaoCredito)).build();

        }catch (Exception e){

            LOG.info("erro ao rodar Requisição insert()");
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @Transactional
    @Override
    public Response delete(long id) {
        try {
            LOG.info("Requisição delete()");
            CartaoCredito cartaoCredito = repository.findById(id);
            if (cartaoCredito != null) {
                repository.delete(cartaoCredito);
                return Response.ok().build();
            } else {
                throw new Exception("cartaoCredito não encontrado!");
            }
        }catch (Exception e){

            LOG.info("erro ao rodar Requisição delete()");
            return Response.notModified(e.getMessage()).build();
        }
    }
}
