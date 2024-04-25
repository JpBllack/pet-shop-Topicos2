package br.projeto.petshop.service;

import br.projeto.petshop.dto.EstadoDTO;
import br.projeto.petshop.model.Estado;
import br.projeto.petshop.repository.EstadoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class EstadoServiceImpl implements EstadoService {

    @Inject
    EstadoRepository estadoRepository;

    private static final Logger LOG = Logger.getLogger(EstadoServiceImpl.class);

    @Override
    public List<EstadoDTO> getAll() {
        List<Estado> estados = estadoRepository.listAll();
        if (estados.isEmpty()) {
            throw new NotFoundException("Não há estados disponíveis");
        }
        return estados.stream().map(EstadoDTO::valueOf).collect(Collectors.toList());
    }

    @Override
    public EstadoDTO getById(long id) {
        Estado estado = estadoRepository.findById(id);
        if (estado == null) {
            throw new NotFoundException("Estado não encontrado");
        }
        return EstadoDTO.valueOf(estado);
    }

    @Override
    @Transactional
    public void insert(EstadoDTO estadoDTO) {
        Estado estado = new Estado();
        estado.setNome(estadoDTO.nome());
        estado.setSigla(estadoDTO.sigla());
        estadoRepository.persist(estado);
        LOG.info("Estado inserido com sucesso: " + estado);
    }

    @Override
    @Transactional
    public void update(long id, EstadoDTO estadoDTO) {
        Estado estado = estadoRepository.findById(id);
        if (estado == null) {
            LOG.warn("Estado não encontrado para o ID: " + id);
            throw new NotFoundException("Estado não encontrado");
        }
        estado.setNome(estadoDTO.nome());
        estado.setSigla(estadoDTO.sigla());
        estadoRepository.update(estado);
        LOG.info("Estado atualizado com sucesso: " + estado);
    }

    @Override
    @Transactional
    public void delete(long id) {
        if (!estadoRepository.deleteById(id)) {
            LOG.warn("Estado não encontrado para o ID: " + id);
            throw new NotFoundException("Estado não encontrado");
        }
        LOG.info("Estado deletado com sucesso");
    }
}
