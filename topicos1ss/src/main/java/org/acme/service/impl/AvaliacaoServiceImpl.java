package org.acme.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import org.acme.dto.AvaliacaoDTO;
import org.acme.dto.AvaliacaoResponseDTO;
import org.acme.model.Avaliacao;
import org.acme.repository.AvaliacaoRepository;
import org.acme.service.AvaliacaoService;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class AvaliacaoServiceImpl implements AvaliacaoService {

    public static final Logger LOG = Logger.getLogger(AvaliacaoServiceImpl.class);
    @Inject
    AvaliacaoRepository repository;

    @Override
    public List<AvaliacaoResponseDTO> getAll() {
        try {
            LOG.info("Requisição getAll()");
            return repository.findAll().stream()
                    .map(AvaliacaoResponseDTO::new)
                    .collect(Collectors.toList());

        }catch (Exception e){
            LOG.error("Erro ao rodar Requisição getAll()");
            return null;
        }
    }

    @Override
    public AvaliacaoResponseDTO getId(Long id) {
        try {
            LOG.info("Requisição getId()");
            return new AvaliacaoResponseDTO(repository.findById(id));

        }catch (Exception e){

            LOG.info("erro ao rodar Requisição getId()");
            return null;
        }
    }

    @Transactional
    @Override
    public Response insert(AvaliacaoDTO dto) {

        try {
            LOG.info("Requisição insert()");
            Avaliacao avaliacao = new Avaliacao();
            avaliacao.setNota(dto.nota());
            avaliacao.setComentario(dto.comentario());
            repository.persist(avaliacao);
            return Response.ok(new AvaliacaoResponseDTO(avaliacao)).build();

        }catch (Exception e){

            LOG.info("erro ao rodar Requisição insert()");
            return Response.status(Status.BAD_REQUEST).build();
        }

    }


    @Transactional
    @Override
    public Response delete(Long id) {
        try {
            LOG.info("Requisição delete()");
            Avaliacao avaliacao = repository.findById(id);
            if (avaliacao != null) {
                repository.delete(avaliacao);
                return Response.ok().build();
            } else {
                throw new Exception("Avaliacao não encontrada!");
            }
        }catch (Exception e){

            LOG.info("erro ao rodar Requisição delete()");
            return Response.notModified(e.getMessage()).build();
        }

    }
}
