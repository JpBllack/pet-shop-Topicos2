package org.acme.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import org.acme.dto.PagamentoDTO;
import org.acme.dto.PagamentoResponseDTO;
import org.acme.model.Pagamento;
import org.acme.repository.PagamentoRepository;
import org.acme.service.PagamentoService;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PagamentoServiceImpl implements PagamentoService {

    public static final Logger LOG = Logger.getLogger(PagamentoServiceImpl.class);

    @Inject
    PagamentoRepository repository;

    @Override
    public List<PagamentoResponseDTO> getAll() {
        try {
            LOG.info("Requisição getAll()");

            return repository.findAll().stream()
                    .map(PagamentoResponseDTO::new)
                    .collect(Collectors.toList());
        }catch (Exception e){
            LOG.error("Erro ao rodar Requisição getAll()");
            return null;
        }
    }

    @Override
    public PagamentoResponseDTO getId(long id) {
        try {
            LOG.info("Requisição getId()");
            return new PagamentoResponseDTO(repository.findById(id));

        }catch (Exception e){

            LOG.info("erro ao rodar Requisição getId()");
            return null;
        }
    }

    @Override
    public List<PagamentoResponseDTO> getByTipo(String tipo) {
        return repository.findByTipo(tipo).stream()
                .map(PagamentoResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Response insert(PagamentoDTO dto) {

        try {
            LOG.info("Requisição insert()");


            Pagamento pagamento = new Pagamento();
            pagamento.setTipo(dto.tipo());
            pagamento.setValor(dto.valor());
            pagamento.setPago(true);
            repository.persist(pagamento);
            return Response.ok(new PagamentoResponseDTO(pagamento)).build();
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
            Pagamento pagamento = repository.findById(id);
            if (pagamento != null) {
                repository.delete(pagamento);
                return Response.ok().build();
        } else {
            throw new Exception("pagamento não encontrado!");
        }
    }catch (Exception e){

        LOG.info("erro ao rodar Requisição delete() - " + e.getMessage());
        return Response.notModified(e.getMessage()).build();
    }


    }
}
