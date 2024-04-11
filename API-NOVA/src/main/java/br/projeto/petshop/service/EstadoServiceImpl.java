package br.projeto.petshop.service;

import br.projeto.petshop.dto.EstadoDTO;
import br.projeto.petshop.model.Estado;
import br.projeto.petshop.repository.EstadoRepository;
import br.projeto.petshop.validation.ValidationException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class EstadoServiceImpl implements EstadoService {

    private static final Logger LOG = Logger.getLogger(EstadoServiceImpl.class);

    @Inject
    private EstadoRepository estadoRepository;

    @Override
    public List<EstadoDTO> getAll() {
        LOG.info("Buscando todos os estados");
        List<EstadoDTO> estados = estadoRepository.listAll().stream()
                .map(EstadoDTO::new)
                .collect(Collectors.toList());
        LOG.info("Estados encontrados: " + estados.size());
        return estados;
    }

    @Override
    public EstadoDTO getById(long id) {
        LOG.info("Buscando estado pelo ID: " + id);
        Estado estado = estadoRepository.findById(id);
        if (estado == null) {
            LOG.warn("Estado não encontrado para o ID: " + id);
            throw new NotFoundException("Estado não encontrado");
        }
        LOG.info("Estado encontrado: " + estado);
        return new EstadoDTO(estado);
    }

    @Override
    public EstadoDTO getBySigla(String sigla) {
        LOG.info("Buscando estado pela sigla: " + sigla);
        Estado estado = estadoRepository.findBySigla(sigla);
        if (estado == null) {
            LOG.warn("Estado não encontrado para a sigla: " + sigla);
            throw new NotFoundException("Estado não encontrado");
        }
        LOG.info("Estado encontrado: " + estado);
        return new EstadoDTO(estado);
    }

    @Override
    @Transactional
    public void insert(EstadoDTO estadoDTO) {
        LOG.info("Inserindo novo estado: " + estadoDTO);
        if (estadoDTO.sigla() == null || estadoDTO.nome() == null) {
            throw new ValidationException("Sigla", "Sigla e nome do estado devem ser informados");

            
        }
        Estado estado = new Estado();
        estado.setSigla(estadoDTO.sigla());
        estado.setNome(estadoDTO.nome());
        estadoRepository.persist(estado);
        LOG.info("Estado inserido com sucesso: " + estado);
    }

    @Override
    @Transactional
    public void update(long id, EstadoDTO estadoDTO) {
        LOG.info("Atualizando estado com ID: " + id + ", dados: " + estadoDTO);
        Estado estado = estadoRepository.findById(id);
        if (estado == null) {
            LOG.warn("Estado não encontrado para o ID: " + id);
            throw new NotFoundException("Estado não encontrado");
        }
        estado.setSigla(estadoDTO.sigla());
        estado.setNome(estadoDTO.nome());
        estadoRepository.persist(estado);
        LOG.info("Estado atualizado com sucesso: " + estado);
    }

    @Override
    @Transactional
    public void delete(long id) {
        LOG.info("Deletando estado com ID: " + id);
        if (!estadoRepository.deleteById(id)) {
            LOG.warn("Estado não encontrado para o ID: " + id);
            throw new NotFoundException("Estado não encontrado");
        }
        LOG.info("Estado deletado com sucesso");
    }
}
