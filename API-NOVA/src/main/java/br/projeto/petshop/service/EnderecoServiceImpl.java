package br.projeto.petshop.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;


import org.jboss.logging.Logger;

import br.projeto.petshop.dto.EnderecoDTO;
import br.projeto.petshop.dto.EnderecoResponseDTO;
import br.projeto.petshop.model.Endereco;
import br.projeto.petshop.repository.EnderecoRepository;
import br.projeto.petshop.repository.MunicipioRepository;
import br.projeto.petshop.repository.UsuarioRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class EnderecoServiceImpl implements EnderecoService {
    public static final Logger LOG = Logger.getLogger(EnderecoServiceImpl.class);
    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    MunicipioRepository municipioRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    public List<EnderecoResponseDTO> getAll() {
        try {
            LOG.info("Requisição getAll()");

            return enderecoRepository.findAll().stream()
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
            return new EnderecoResponseDTO(enderecoRepository.findById(id));

        }catch (Exception e){

            LOG.info("erro ao rodar Requisição getId()");
            return null;
        }
    }

    @Transactional
    @Override
    public Response insert(EnderecoDTO dto, Long userId) {
        LOG.info("Requisição insert() iniciada.");
        try {
            Endereco endereco = new Endereco();
            endereco.setLogradouro(dto.logradouro());
            endereco.setNumero(dto.numero());
            endereco.setComplemento(dto.complemento());
            endereco.setBairro(dto.bairro());
            endereco.setMunicipio(municipioRepository.findById(dto.idCidade()));
            endereco.setCep(dto.cep());
            endereco.setUsuario(usuarioRepository.findById(userId));
    
            LOG.info("Dados do endereço para inserção: " + endereco);
    
            enderecoRepository.persist(endereco);
    
            LOG.info("Requisição insert() concluída com sucesso.");
            return Response.ok(new EnderecoResponseDTO(endereco)).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição insert(): " + e.getMessage(), e);
            return Response.status(Status.BAD_REQUEST).entity("Erro ao inserir o endereço.").build();
        }
    }
    

        @Transactional
        @Override
        public Response update(long id, EnderecoDTO dto) {
            try {
                LOG.info("Requisição update()");
                Endereco endereco = enderecoRepository.findById(id);
                if (endereco != null) {
                    endereco.setLogradouro(dto.logradouro());
                    endereco.setNumero(dto.numero());
                    endereco.setComplemento(dto.complemento());
                    endereco.setBairro(dto.bairro());
                    endereco.setMunicipio(municipioRepository.findById(dto.idCidade()));
                    endereco.setCep(dto.cep());
                    enderecoRepository.persist(endereco);
                    return Response.ok(new EnderecoResponseDTO(endereco)).build();
                } else {
                    LOG.error("Endereço não encontrado para o ID: " + id);
                    return Response.status(Status.NOT_FOUND).entity("Endereço não encontrado").build();
                }
            } catch (Exception e) {
                LOG.error("Erro ao rodar Requisição update()", e);
                return Response.status(Status.BAD_REQUEST).build();
            }
        }

    @Transactional
    @Override
    public Response delete(long id) {
        LOG.info("Requisição delete() para ID: " + id);
        try {
            Endereco endereco = enderecoRepository.findById(id);
            if (endereco != null) {
                enderecoRepository.delete(endereco);
                LOG.info("Endereço deletado com sucesso para ID: " + id);
                return Response.ok().build();
            } else {
                LOG.warn("Endereço não encontrado para o ID: " + id);
                throw new NotFoundException("Endereço não encontrado");
            }
        } catch (NotFoundException e) {
            LOG.warn("Erro ao rodar Requisição delete(): " + e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição delete()", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro interno do servidor").build();
        }
    }

}
