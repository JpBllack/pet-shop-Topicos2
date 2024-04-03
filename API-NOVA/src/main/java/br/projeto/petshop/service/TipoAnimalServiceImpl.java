package br.projeto.petshop.service;

import java.util.List;

import br.projeto.petshop.dto.TipoAnimalDTO;
import br.projeto.petshop.model.TipoAnimal;
import br.projeto.petshop.repository.TipoAnimalRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import br.projeto.petshop.validation.ValidationException;

@ApplicationScoped
public class TipoAnimalServiceImpl implements TipoAnimalService{

    @Inject
    TipoAnimalRepository repository;

    @Override
    public List<TipoAnimalDTO> getAll() {
        if(repository.listAll().stream().map(e -> TipoAnimalDTO.valueOf(e)).toList().isEmpty()){
            throw new NotFoundException("não há tipos");
        }
        return repository.listAll().stream().map(e -> TipoAnimalDTO.valueOf(e)).toList();
    }

    @Override
    public TipoAnimalDTO insert(TipoAnimalDTO dto) {
        if(dto.nome().isBlank() || dto.nome().isEmpty()){
            throw new ValidationException("400", "O valor não pode estar em branco");
        }

        if(repository.existsByNome(dto.nome())){
            throw new ValidationException("400", "O animal já existe");
        }

        TipoAnimal newTipo = new TipoAnimal();

        newTipo.setNome(dto.nome());

        repository.persist(newTipo);

        return TipoAnimalDTO.valueOf(newTipo);
    }

    @Override
    public TipoAnimalDTO update(Long id, TipoAnimalDTO dto) {
        if(repository.findById(id) == null){
            throw new NotFoundException("id não encontrado");
        }

        TipoAnimal tipoAnimal = repository.findById(id);
        tipoAnimal.setNome(dto.nome());

        return TipoAnimalDTO.valueOf(tipoAnimal);
    }

    @Override
    public void delete(Long id) {
        if(!repository.deleteById(id)){
            throw new NotFoundException("Id não encontrado");
        }
    }

    @Override
    public TipoAnimalDTO findById(Long id) {
        if(repository.findById(id) == null) {
            throw new NotFoundException("Id não encontrado");
        }
        return TipoAnimalDTO.valueOf(repository.findById(id));
    }

    @Override
    public TipoAnimalDTO findByNome(String nome) {
        if(repository.findByNome(nome) == null) {
            throw new NotFoundException("Tipo não encontrado");
        }
        return TipoAnimalDTO.valueOf(repository.findByNome(nome));
    }
    
}
