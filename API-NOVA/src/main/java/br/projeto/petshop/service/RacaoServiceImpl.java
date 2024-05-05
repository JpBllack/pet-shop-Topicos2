package br.projeto.petshop.service;

import java.util.List;

import org.jboss.logging.Logger;

import br.projeto.petshop.dto.RacaoDTO;
import br.projeto.petshop.dto.RacaoResponseDTO;
import br.projeto.petshop.dto.TipoAnimalDTO;
import br.projeto.petshop.model.Idade;
import br.projeto.petshop.model.Peso;
import br.projeto.petshop.model.Racao;
import br.projeto.petshop.model.TipoAnimal;
import br.projeto.petshop.repository.MarcaRepository;
import br.projeto.petshop.repository.RacaoRepository;
import br.projeto.petshop.repository.TipoAnimalRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import br.projeto.petshop.validation.ValidationException;

@ApplicationScoped
public class RacaoServiceImpl implements RacaoService {

    @Inject
    RacaoRepository racaoRepository;
    @Inject
    TipoAnimalRepository tipoAnimalRepository;
    @Inject
    MarcaRepository marcaRepository;


    private static final Logger LOG = Logger.getLogger(EstadoServiceImpl.class);


    @Override
    public List<RacaoResponseDTO> getAll() {
        if(racaoRepository.listAll().isEmpty()) {
            throw new NotFoundException("Não há rações");
        }
        return racaoRepository.listAll().stream().map(RacaoResponseDTO::valueOf).toList();
    }

    @Override
    public RacaoResponseDTO getById(long id) {
        Racao racao = racaoRepository.findById(id);
        if (racao == null) {
            throw new NotFoundException("Ração não encontrada");
        }
        return RacaoResponseDTO.valueOf(racao);
    }

    @Override
    public List<RacaoResponseDTO> getBySabor(String sabor) {
        List<Racao> racoes = racaoRepository.findBySabor(sabor);
        if (racoes.isEmpty()) {
            throw new NotFoundException("Nenhuma ração encontrada com o sabor especificado");
        }
        return racoes.stream().map(RacaoResponseDTO::valueOf).toList();
    }

    @Override
    @Transactional
    public RacaoResponseDTO insert(RacaoDTO racaoDTO) {
    
        // Cria uma instância de TipoAnimalRepository
        TipoAnimalRepository tipoAnimalRepository = new TipoAnimalRepository();
    
        Racao racao = new Racao();
        racao.setSabor(racaoDTO.sabor());
        racao.setAnimal(tipoAnimalRepository.findById(racaoDTO.animal()));
        racao.setPeso(Peso.valueOf(racaoDTO.peso().getId()));
        racao.setIdade(Idade.valueOf(racaoDTO.idade().getId()));
        racao.setMarca(marcaRepository.findById(racaoDTO.marca()));
        
        racaoRepository.persist(racao);

        return RacaoResponseDTO.valueOf(racao);
    }

    @Override
    @Transactional
    public RacaoResponseDTO update(long id, RacaoDTO racaoDTO) {
        LOG.info("Atualizando ração com ID: " + id + ", dados: " + racaoDTO);
        Racao racao = racaoRepository.findById(id);
        if (racao == null) {
            LOG.warn("Ração não encontrada para o ID: " + id);
            throw new NotFoundException("Ração não encontrada");
        }
        racao.setSabor(racaoDTO.sabor());
        racao.setAnimal(tipoAnimalRepository.findById(racaoDTO.animal()));
        racao.setPeso(Peso.valueOf(racaoDTO.peso().getId()));
        racao.setIdade(Idade.valueOf(racaoDTO.idade().getId()));;
        racao.setMarca(marcaRepository.findById(racaoDTO.marca()));

        return RacaoResponseDTO.valueOf(racao);
    }

    @Override
    @Transactional
    public void delete(long id) {
        LOG.info("Deletando ração com ID: " + id);
        if (!racaoRepository.deleteById(id)) {
            LOG.warn("Ração não encontrada para o ID: " + id);
            throw new NotFoundException("Ração não encontrada");
        }
        LOG.info("Ração deletada com sucesso");
    }

    @Override
    @Transactional
    public RacaoResponseDTO changeImage(Long id, String ImageName) {
        Racao racao = racaoRepository.findById(id);
        racao.setImagem(ImageName);
        return RacaoResponseDTO.valueOf(racao);
    }
}
