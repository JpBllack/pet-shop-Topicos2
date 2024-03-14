package org.acme.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import org.acme.dto.AvaliacaoResponseDTO;
import org.acme.dto.BoletoBancarioDTO;
import org.acme.dto.BoletoBancarioResponseDTO;
import org.acme.model.Avaliacao;
import org.acme.model.BoletoBancario;
import org.acme.repository.BoletoBancarioRepository;
import org.acme.service.BoletoBancarioService;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class BoletoBancarioServiceImpl implements BoletoBancarioService {
    public static final Logger LOG = Logger.getLogger(BoletoBancarioServiceImpl.class);
    @Inject
    BoletoBancarioRepository repository;

    @Override
    public List<BoletoBancarioResponseDTO> getAll() {
        try {
            LOG.info("Requisição getAll()");

            return repository.findAll().stream()
                    .map(BoletoBancarioResponseDTO::new)
                    .collect(Collectors.toList());
        }catch (Exception e){
            LOG.error("Erro ao rodar Requisição getAll()");
            return null;
        }
    }

    @Override
    public BoletoBancarioResponseDTO getId(long id) {

        try {
            LOG.info("Requisição getId()");
            return new BoletoBancarioResponseDTO(repository.findById(id));
        }catch (Exception e){

            LOG.info("erro ao rodar Requisição getId()");
            return null;
        }
    }

    @Transactional
    @Override
    public Response insert(BoletoBancarioDTO dto) {

        try {
            LOG.info("Requisição insert()");

            BoletoBancario boletoBancario = new BoletoBancario();
            boletoBancario.setBanco(dto.banco());
            boletoBancario.setNumeroBoleto(dto.numeroBoleto());
            boletoBancario.setValor(dto.valor());
            boletoBancario.setPago(true);
            repository.persist(boletoBancario);
            return Response.ok(new BoletoBancarioResponseDTO(boletoBancario)).build();

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
            BoletoBancario boletoBancario = repository.findById(id);
            if (boletoBancario != null) {
                repository.delete(boletoBancario);
                return Response.ok().build();
            } else {
                throw new Exception("boleto não encontrado!");
            }
        }catch (Exception e){

            LOG.info("erro ao rodar Requisição delete()");
            return Response.notModified(e.getMessage()).build();
        }
    }
}
