package org.acme.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import org.acme.dto.PixPagamentoDTO;
import org.acme.dto.PixPagamentoResponseDTO;
import org.acme.model.PixPagamento;
import org.acme.repository.PixPagamentoRepository;
import org.acme.service.PixPagamentoService;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PixPagamentoServiceImpl implements PixPagamentoService {

    public static final Logger LOG = Logger.getLogger(PixPagamentoServiceImpl.class);

    @Inject
    PixPagamentoRepository repository;

    @Override
    public List<PixPagamentoResponseDTO> getAll() {
        try {
            LOG.info("Requisição getAll()");
            return repository.findAll().stream()
                    .map(PixPagamentoResponseDTO::new)
                    .collect(Collectors.toList());

        }catch (Exception e){
            LOG.error("Erro ao rodar Requisição getAll()");
            return null;
        }
    }

    @Override
    public PixPagamentoResponseDTO getId(long id) {
        try {
            LOG.info("Requisição getId()");
            return new PixPagamentoResponseDTO(repository.findById(id));

        }catch (Exception e){

            LOG.info("erro ao rodar Requisição getId()");
            return null;
        }
    }

    @Transactional
    @Override
    public Response insert(PixPagamentoDTO dto) {

        try {
            LOG.info("Requisição insert()");

            PixPagamento pixPagamento = new PixPagamento();
            pixPagamento.setChave(dto.chave());
            pixPagamento.setValor(dto.valor());
            repository.persist(pixPagamento);
            return Response.ok(new PixPagamentoResponseDTO(pixPagamento)).build();

        }catch (Exception e){

            LOG.info("erro ao rodar Requisição insert()");
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @Transactional
    @Override
    public Response update(Long id, PixPagamentoDTO dto) {
        PixPagamento pixPagamento = repository.findById(id);
        if (pixPagamento != null) {
            pixPagamento.setChave(dto.chave());
            pixPagamento.setValor(dto.valor());
            repository.persist(pixPagamento);
            return Response.ok(new PixPagamentoResponseDTO(pixPagamento)).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @Transactional
    @Override
    public Response delete(Long id) {
        try {
            LOG.info("Requisição delete()");
            PixPagamento pixPagamento = repository.findById(id);
            if (pixPagamento != null) {
                repository.delete(pixPagamento);
                return Response.ok().build();
            } else {
                throw new Exception("pixPagamento não encontrado!");
            }
        } catch (Exception e) {

            LOG.info("erro ao rodar Requisição delete() - " + e.getMessage());
            return Response.notModified(e.getMessage()).build();
        }
    }
}
