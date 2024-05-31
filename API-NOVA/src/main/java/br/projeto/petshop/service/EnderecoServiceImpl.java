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

        // Se o novo endereço for definido como principal, desmarque todos os outros do usuário
        if (dto.isPrincipal()) {
            List<Endereco> userEnderecos = usuarioRepository.findEnderecosByUsuario(userId);
            for (Endereco e : userEnderecos) {
                e.setPrincipal(false);
                enderecoRepository.persist(e);
            }
        }

        endereco.setPrincipal(dto.isPrincipal());
        enderecoRepository.persist(endereco);

        return EnderecoResponseDTO.valueOf(endereco);
    }

    @Override
    @Transactional
    public EnderecoResponseDTO update(long id, EnderecoDTO dto) {
        LOG.info("Requisição update() para isPrincipal");

        Endereco endereco = enderecoRepository.findById(id);
        if (endereco == null) {
            LOG.error("Endereço não encontrado para o ID: " + id);
            throw new NotFoundException();
        }

        Long userId = endereco.getUsuario().getId();
        List<Endereco> userEnderecos = usuarioRepository.findEnderecosByUsuario(userId);

        // Se o endereço atualizado for definido como principal, desmarque todos os outros do usuário
        if (dto.isPrincipal()) {
            for (Endereco e : userEnderecos) {
                if (!e.getId().equals(id)) {
                    e.setPrincipal(false);
                    enderecoRepository.persist(e);
                }
            }
        }

        endereco.setPrincipal(dto.isPrincipal());
        enderecoRepository.persist(endereco);

        return EnderecoResponseDTO.valueOf(endereco);
    }


    @Override
    @Transactional
    public void delete(long id) {
        LOG.info("Requisição delete() para ID: " + id);
        Endereco endereco = enderecoRepository.findById(id);
        if (endereco == null) {
            throw new NotFoundException("Endereço não encontrado");
        }

        // Verifica se o endereço a ser excluído é o principal
        if (endereco.isPrincipal()) {
            Long userId = endereco.getUsuario().getId();
            List<Endereco> userEnderecos = usuarioRepository.findEnderecosByUsuario(userId);

            // Procura por outro endereço para definir como principal
            for (Endereco e : userEnderecos) {
                if (!e.getId().equals(id)) {
                    // Se encontrar outro endereço, define-o como principal e sai do loop
                    e.setPrincipal(true);
                    enderecoRepository.persist(e);
                    break;
                }
            }
        }

        // Exclui o endereço
        if (!enderecoRepository.deleteById(id)) {
            throw new NotFoundException("Endereço não encontrado");
        }
    }

    @Override
    @Transactional
    public EnderecoResponseDTO setPrincipal(Long id) {
        // Recupere o endereço pelo ID
        Endereco endereco = enderecoRepository.findById(id);
        if (endereco == null) {
            throw new NotFoundException("Endereço não encontrado");
        }

        // Verifique se o endereço pertence ao usuário
        Long userId = endereco.getUsuario().getId();
        List<Endereco> userEnderecos = usuarioRepository.findEnderecosByUsuario(userId);
        boolean enderecoEncontrado = false;
        for (Endereco e : userEnderecos) {
            if (e.getId().equals(id)) {
                enderecoEncontrado = true;
                e.setPrincipal(true);
                enderecoRepository.persist(e);
            } else {
                e.setPrincipal(false);
                enderecoRepository.persist(e);
            }
        }
        if (!enderecoEncontrado) {
            throw new NotFoundException("O endereço não pertence ao usuário");
        }

        return EnderecoResponseDTO.valueOf(endereco);
    }
}
