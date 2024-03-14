package org.acme.service.impl;

import java.util.List;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import org.acme.dto.EstadoDTO;
import org.acme.dto.EstadoResponseDTO;
import org.acme.model.Estado;
import org.acme.repository.EstadoRepository;
import org.acme.service.EstadoService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.jboss.logging.Logger;

@ApplicationScoped
public class EstadoServiceImpl implements EstadoService {

    public static final Logger LOG = Logger.getLogger(EstadoServiceImpl.class);

    @Inject
    EstadoRepository repository;

    @Override
    @Transactional
    public Response insert(EstadoDTO dto) {

        try {
            LOG.info("Requisição insert()");

            Estado novoEstado = new Estado();
            novoEstado.setnome(dto.nome());
            novoEstado.setsigla(dto.sigla());

            repository.persist(novoEstado);
            return Response.ok(new EstadoResponseDTO(novoEstado)).build();

        }catch (Exception e){

            LOG.info("erro ao rodar Requisição insert()");
            return Response.status(Status.BAD_REQUEST).build();
        }
     }

    @Override
    @Transactional
    public EstadoResponseDTO update(EstadoDTO dto, Long id) {
        
        Estado estado = repository.findById(id);
        if (estado != null) {
            estado.setnome(dto.nome());
            estado.setsigla(dto.sigla());
        } else 
            throw new NotFoundException();

        return new EstadoResponseDTO(estado);
    }

    @Override
    @Transactional
    public Response delete(Long id) {
        try {
            LOG.info("Requisicao delete()");
            if (!repository.deleteById(id))
                throw new NotFoundException();
            return Response.ok().build();
        }catch (Exception e){
            LOG.error("Erro na requisição delete() -" + e.getMessage());
            return Response.notModified(e.getMessage()).build();
        }

    }

    @Override
    public EstadoResponseDTO findById(Long id) {
        try {
            LOG.info("Requisição getId()");
            return new EstadoResponseDTO(repository.findById(id));

        }catch (Exception e){

            LOG.info("erro ao rodar Requisição getId()");
            return null;
        }
    }

    @Override
    public List<EstadoResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream()
            .map(e -> new EstadoResponseDTO(e)).toList();
    }

    @Override
    public List<EstadoResponseDTO> findAll() {
        try {
            LOG.info("Requisição getAll()");

            return repository.listAll().stream()
                    .map(e -> new EstadoResponseDTO(e)).toList();
        }catch (Exception e){
            LOG.error("Erro ao rodar Requisição getAll()");
            return null;
        }
    }
}
