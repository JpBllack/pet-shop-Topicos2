package br.projeto.petshop.service;

import java.util.List;

import org.jboss.logging.Logger;

import br.projeto.petshop.dto.RacaoDTO;
import br.projeto.petshop.model.Idade;
import br.projeto.petshop.model.Peso;
import br.projeto.petshop.model.Racao;
import br.projeto.petshop.model.TipoAnimal;
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


    private static final Logger LOG = Logger.getLogger(EstadoServiceImpl.class);


    @Override
    public List<RacaoDTO> getAll() {
        if(racaoRepository.listAll().isEmpty()) {
            throw new NotFoundException("Não há rações");
        }
        return racaoRepository.listAll().stream().map(RacaoDTO::valueOf).toList();
    }

    @Override
    public RacaoDTO getById(long id) {
        Racao racao = racaoRepository.findById(id);
        if (racao == null) {
            throw new NotFoundException("Ração não encontrada");
        }
        return RacaoDTO.valueOf(racao);
    }

    @Override
    public List<RacaoDTO> getBySabor(String sabor) {
        List<Racao> racoes = racaoRepository.findBySabor(sabor);
        if (racoes.isEmpty()) {
            throw new NotFoundException("Nenhuma ração encontrada com o sabor especificado");
        }
        return racoes.stream().map(RacaoDTO::valueOf).toList();
    }

    @Override
    @Transactional
    public void insert(RacaoDTO racaoDTO) {
        Racao existingRacao = racaoRepository.findById(racaoDTO.id());
        if (existingRacao != null) {
        throw new ValidationException("400", "Uma ração com este ID já existe");
        }

    
        // Cria uma instância de TipoAnimalRepository
        TipoAnimalRepository tipoAnimalRepository = new TipoAnimalRepository();
    
        // Verifica se o TipoAnimal já existe no banco de dados
        TipoAnimal tipoAnimal = tipoAnimalRepository.findById(racaoDTO.animal().id());
        if (tipoAnimal == null) {
            // Se o TipoAnimal não existir, cria uma nova instância e persiste
            tipoAnimal = new TipoAnimal();
            tipoAnimal.setNome(racaoDTO.animal().nome());
            tipoAnimalRepository.persist(tipoAnimal);
        }
    
        Racao racao = new Racao();
        racao.setSabor(racaoDTO.sabor());
        racao.setAnimal(tipoAnimal);
    
        Peso peso = racaoDTO.peso();
        racao.setPeso(peso);
        Idade idade = racaoDTO.idade();
        racao.setIdade(idade);
    
        
        // Agora que o TipoAnimal está persistido, podemos salvar a Racao
        racaoRepository.persist(racao);
    }

    @Override
    @Transactional
    public void update(long id, RacaoDTO racaoDTO) {
        LOG.info("Atualizando ração com ID: " + id + ", dados: " + racaoDTO);
        Racao racao = racaoRepository.findById(id);
        if (racao == null) {
            LOG.warn("Ração não encontrada para o ID: " + id);
            throw new NotFoundException("Ração não encontrada");
        }
        racao.setSabor(racaoDTO.sabor());
        racao.setAnimal(racaoDTO.animal().toModel());
        racao.setPeso(racaoDTO.peso());
        racao.setIdade(racaoDTO.idade());
        racaoRepository.persist(racao);
        LOG.info("Ração atualizada com sucesso: " + racao);
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
}
