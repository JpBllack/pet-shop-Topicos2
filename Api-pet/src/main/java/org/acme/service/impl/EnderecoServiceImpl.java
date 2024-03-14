package org.acme.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import org.acme.dto.EnderecoDTO;
import org.acme.dto.EnderecoResponseDTO;
import org.acme.model.Endereco;
import org.acme.repository.CidadeRepository;
import org.acme.repository.EnderecoRepository;
import org.acme.repository.UsuarioRepository;
import org.acme.service.EnderecoService;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class EnderecoServiceImpl implements EnderecoService {
    public static final Logger LOG = Logger.getLogger(EnderecoServiceImpl.class);
    @Inject
    EnderecoRepository repository;

    @Inject
    CidadeRepository cidadeRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    public List<EnderecoResponseDTO> getAll() {
        try {
            LOG.info("Requisição getAll()");

            return repository.findAll().stream()
                    .map(EnderecoResponseDTO::new)
                    .collect(Collectors.toList());
        }catch (Exception e){
            LOG.error("Erro ao rodar Requisição getAll()");
            return null;
        }
    }

    @Override
    public EnderecoResponseDTO getId(Long id) {
        try {
            LOG.info("Requisição getId()");
            return new EnderecoResponseDTO(repository.findById(id));

        }catch (Exception e){

            LOG.info("erro ao rodar Requisição getId()");
            return null;
        }
    }

    @Transactional
    @Override
    public Response insert(String idUsuario, EnderecoDTO dto) {

        try {
            LOG.info("Requisição insert()");

            Endereco endereco = new Endereco();
            endereco.setLogradouro(dto.logradouro());
            endereco.setNumero(dto.numero());
            endereco.setComplemento(dto.complemento());
            endereco.setBairro(dto.bairro());
            endereco.setCidade(cidadeRepository.findById(dto.idCidade()));
            endereco.setCep(dto.cep());
            endereco.setUsuario(usuarioRepository.findById(idUsuario));
            repository.persist(endereco);
            return Response.ok(new EnderecoResponseDTO(endereco)).build();

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
            Endereco endereco = repository.findById(id);
            if (endereco != null) {
                repository.delete(endereco);
                return Response.ok().build();
        } else {
            throw new Exception("endereco não encontrado!");
        }
    }catch (Exception e){

        LOG.info("erro ao rodar Requisição delete()");
        return Response.notModified(e.getMessage()).build();
    }
    }
}
