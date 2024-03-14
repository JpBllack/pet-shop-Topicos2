package org.acme.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import org.acme.dto.TelefoneDTO;
import org.acme.dto.TelefoneResponseDTO;
import org.acme.model.Telefone;
import org.acme.model.Usuario;
import org.acme.repository.TelefoneRepository;
import org.acme.repository.UsuarioRepository;
import org.acme.service.TelefoneService;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class TelefoneServiceImpl implements TelefoneService {

    public static final Logger LOG = Logger.getLogger(TelefoneServiceImpl.class);

    @Inject
    TelefoneRepository repository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    public List<TelefoneResponseDTO> getAll() {
        try {
            LOG.info("Requisição getAll()");
            return repository.findAll().stream()
                    .map(TelefoneResponseDTO::new)
                    .collect(Collectors.toList());

        }catch (Exception e){
            LOG.error("Erro ao rodar Requisição getAll()");
            return null;
        }
    }

    @Override
    public TelefoneResponseDTO getId(long id) {
        try {
            LOG.info("Requisição getId()");
            return new TelefoneResponseDTO(repository.findById(id));

        }catch (Exception e){

            LOG.info("erro ao rodar Requisição getId()");
            return null;
        }
    }

    @Override
    public List<TelefoneResponseDTO> getCodigoArea(String codigoArea) {
        return repository.findByCodigoArea(codigoArea).stream()
                .map(TelefoneResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Response insert(TelefoneDTO dto, String idUsuario) {

        try {
            LOG.info("Requisição insert()");

            Telefone telefone = new Telefone();
            telefone.setCodigoArea(dto.codigoArea());
            telefone.setNumero(dto.numero());
            Usuario u = usuarioRepository.findById(idUsuario);
            telefone.setUsuario(u);
            repository.persist(telefone);
            return Response.ok(new TelefoneResponseDTO(telefone)).build();

        }catch (Exception e){

            LOG.info("erro ao rodar Requisição insert()");
            return Response.status(Status.BAD_REQUEST).build();
        }
    }

    @Transactional
    @Override
    public Response update(Long id, TelefoneDTO dto) {
        try {

            Telefone telefone = new Telefone();
            telefone = repository.findById(id);
            if (telefone == null) {
                throw new Exception("Telefone nao encontrado");
            }
            telefone.setCodigoArea(dto.codigoArea());
            telefone.setNumero(dto.numero());
            repository.persist(telefone);
            return Response.ok(new TelefoneResponseDTO(telefone)).build();

        }catch (Exception e){
            return Response.status(Status.BAD_REQUEST).build();

        }
    }

    @Transactional
    @Override
    public Response delete(Long id) {
        try {
            LOG.info("Requisição delete()");Telefone telefone = repository.findById(id);
            if (telefone != null) {
                repository.delete(telefone);
                return Response.ok().build();

        } else {
            throw new Exception("telefone não encontrado!");
        }
    }catch (Exception e){

        LOG.info("erro ao rodar Requisição delete() - " + e.getMessage());
        return Response.notModified(e.getMessage()).build();
    }

    }
}
