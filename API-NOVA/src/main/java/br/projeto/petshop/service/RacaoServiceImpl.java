package br.projeto.petshop.service;

import java.util.List;

import br.projeto.petshop.dto.RacaoDTO;
import br.projeto.petshop.model.Idade;
import br.projeto.petshop.model.Peso;
import br.projeto.petshop.model.Racao;
import br.projeto.petshop.model.TipoAnimal;
import br.projeto.petshop.repository.RacaoRepository;
import br.projeto.petshop.repository.TipoAnimalRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import br.projeto.petshop.validation.ValidationException;

@ApplicationScoped
public class RacaoServiceImpl implements RacaoService {

    @Inject
    RacaoRepository repository;

    @Override
    public List<RacaoDTO> getAll() {
        if(repository.listAll().isEmpty()) {
            throw new NotFoundException("Não há rações");
        }
        return repository.listAll().stream().map(RacaoDTO::valueOf).toList();
    }

    @Override
    public RacaoDTO getById(long id) {
        Racao racao = repository.findById(id);
        if (racao == null) {
            throw new NotFoundException("Ração não encontrada");
        }
        return RacaoDTO.valueOf(racao);
    }

    @Override
    public List<RacaoDTO> getBySabor(String sabor) {
        List<Racao> racoes = repository.findBySabor(sabor);
        if (racoes.isEmpty()) {
            throw new NotFoundException("Nenhuma ração encontrada com o sabor especificado");
        }
        return racoes.stream().map(RacaoDTO::valueOf).toList();
    }

    @Override
    public void insert(RacaoDTO racaoDTO) {
        if (repository.existsBySabor(racaoDTO.sabor())) {
            throw new ValidationException("400", "A ração com este sabor já existe");
        }
    
        // Cria uma instância de TipoAnimalRepository
        TipoAnimalRepository tipoAnimalRepository = new TipoAnimalRepository();
    
        // Verifica se o TipoAnimal já existe no banco de dados
        TipoAnimal tipoAnimal = tipoAnimalRepository.findByNome(racaoDTO.animal().nome());
        if (tipoAnimal == null) {
            // Se o TipoAnimal não existir, cria uma nova instância e persiste
            tipoAnimal = new TipoAnimal();
            tipoAnimal.setNome(racaoDTO.animal().nome());
            tipoAnimalRepository.persist(tipoAnimal);
        }
    
        Racao racao = new Racao();
        racao.setSabor(racaoDTO.sabor());
        racao.setAnimal(tipoAnimal);
    
        Peso peso = Peso.valueOf(racaoDTO.peso());
        racao.setPeso(peso);
        Idade idade = Idade.valueOf(racaoDTO.idade());
        racao.setIdade(idade);
    
        // Agora que o TipoAnimal está persistido, podemos salvar a Racao
        repository.persist(racao);
    }
    


    @Override
    public void delete(long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Ração não encontrada para exclusão");
        }
    }
}
