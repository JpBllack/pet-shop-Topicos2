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

            return enderecoRepository.listAll().stream().map(e -> EnderecoResponseDTO.valueOf(e)).toList();
        } catch (Exception e) {
            LOG.error("Erro ao rodar Requisição getAll()");
            return null;
        }
    }

    @Override
    public EnderecoResponseDTO getId(Long id) {
        try {
            LOG.info("Requisição getId()");
            return EnderecoResponseDTO.valueOf(enderecoRepository.findById(id));

        } catch (Exception e) {

            LOG.info("erro ao rodar Requisição getId()");
            return null;
        }
    }

    @Override
    @Transactional
    public EnderecoResponseDTO insert(EnderecoDTO dto, Long userId) {
        LOG.info("Requisição insert() iniciada.");
        Endereco endereco = new Endereco();

        endereco.setLogradouro(dto.logradouro());
        endereco.setNumero(dto.numero());
        endereco.setComplemento(dto.complemento());
        endereco.setBairro(dto.bairro());
        endereco.setUsuario(usuarioRepository.findById(userId));
        endereco.setMunicipio(municipioRepository.findById(dto.idCidade()));
        endereco.setCep(dto.cep());

        enderecoRepository.persist(endereco);


        return EnderecoResponseDTO.valueOf(endereco);


    }

    @Override
    @Transactional
    public EnderecoResponseDTO update(long id, EnderecoDTO dto) {
        LOG.info("Requisição update()");
            Endereco endereco = enderecoRepository.findById(id);
            if (endereco != null) {
                endereco.setLogradouro(dto.logradouro());
                endereco.setNumero(dto.numero());
                endereco.setComplemento(dto.complemento());
                endereco.setBairro(dto.bairro());
                endereco.setMunicipio(municipioRepository.findById(dto.idCidade()));
                endereco.setCep(dto.cep());
                return EnderecoResponseDTO.valueOf(endereco);
            } else {
                LOG.error("Endereço não encontrado para o ID: " + id);
                throw new NotFoundException();
            }
    }

    @Override
    @Transactional
    public void delete(long id) {
        LOG.info("Requisição delete() para ID: " + id);
        if(!enderecoRepository.deleteById(id)){
            throw new NotFoundException();
        }
    }

}
