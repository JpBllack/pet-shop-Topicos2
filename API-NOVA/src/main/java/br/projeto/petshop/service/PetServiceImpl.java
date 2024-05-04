package br.projeto.petshop.service;

import java.util.List;

import org.jboss.logging.Logger;

import br.projeto.petshop.dto.PetDTO;
import br.projeto.petshop.dto.PetResponseDTO;
import br.projeto.petshop.model.Pet;
import br.projeto.petshop.model.TipoAnimal;
import br.projeto.petshop.repository.PetRepository;
import br.projeto.petshop.repository.TipoAnimalRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import br.projeto.petshop.validation.ValidationException;

@ApplicationScoped
public class PetServiceImpl implements PetService {

    @Inject
    private PetRepository petRepository;
    
    @Inject
    private TipoAnimalRepository tipoAnimalRepository;

    private static final Logger LOG = Logger.getLogger(PetServiceImpl.class);

    @Override
    public List<PetResponseDTO> getAll() {
        List<Pet> pets = petRepository.listAll();
        if (pets.isEmpty()) {
            throw new NotFoundException("Nenhum pet encontrado");
        }
        return pets.stream().map(PetResponseDTO::valueOf).toList();
    }

    @Override
    public PetResponseDTO getById(long id) {
        Pet pet = petRepository.findById(id);
        if (pet == null) {
            throw new NotFoundException("Pet não encontrado");
        }
        return PetResponseDTO.valueOf(pet);
    }

    @Override
public PetResponseDTO getByNome(String nome) {
    Pet pet = petRepository.findByNome(nome);
    if (pet == null) {
        throw new NotFoundException("Pet não encontrado com o nome especificado");
    }
    return PetResponseDTO.valueOf(pet);
}


@Override
@Transactional
public PetResponseDTO insert(PetDTO petDTO) {
    // Verifica se um pet com o mesmo nome já existe
    Pet existingPet = petRepository.findByNome(petDTO.nome());
    if (existingPet != null) {
        throw new ValidationException("400", "Um pet com este nome já existe");
    }

    // Verifica se o TipoAnimal já existe no banco de dados
    TipoAnimal tipoAnimal = tipoAnimalRepository.findById(petDTO.tipoAnimal().getId());
    if (tipoAnimal == null) {
        // Se o TipoAnimal não existir, cria uma nova instância e persiste
        tipoAnimal = new TipoAnimal();
        tipoAnimal.setNome(petDTO.tipoAnimal().getNome());
        tipoAnimalRepository.persist(tipoAnimal);
    }

    // Cria um novo pet e mapeia os dados do DTO para a entidade Pet
    Pet pet = new Pet();
    pet.setNome(petDTO.nome());
    pet.setUsuario(petDTO.usuario());
    pet.setAnoNascimento(petDTO.anoNascimento());
    
    
    // Persist o novo pet no banco de dados
    petRepository.persist(pet);
    
    // Retorna o PetResponseDTO criado a partir do novo pet
    return PetResponseDTO.valueOf(pet);
}

    

    @Override
    @Transactional
    public PetResponseDTO update(long id, PetDTO dto) {
        LOG.info("Atualizando pet com ID: " + id + ", dados: " + dto);
        
        Pet pet = petRepository.findById(id);
        if (pet == null) {
            LOG.warn("Pet não encontrado para o ID: " + id);
            throw new NotFoundException("Pet não encontrado");
        }

        // Verifica a existência do tipo de animal
        TipoAnimal tipoAnimal = tipoAnimalRepository.findById(dto.tipoAnimal().getId());
        if (tipoAnimal == null) {
            throw new ValidationException("400", "Tipo de animal não encontrado");
        }

        // Atualiza os dados do pet
        mapPetFromDTO(pet, dto, tipoAnimal);
        
        petRepository.persist(pet);
        LOG.info("Pet atualizado com sucesso: " + pet);
        return PetResponseDTO.valueOf(pet);
    }

    @Override
    @Transactional
    public void delete(long id) {
        LOG.info("Deletando pet com ID: " + id);
        
        if (!petRepository.deleteById(id)) {
            LOG.warn("Pet não encontrado para o ID: " + id);
            throw new NotFoundException("Pet não encontrado");
        }
        
        LOG.info("Pet deletado com sucesso");
    }

   /*  @Override
    public PetResponseDTO changeImage(long id, String imageName) {
        Pet pet = petRepository.findById(id);
        if (pet == null) {
            throw new NotFoundException("Pet não encontrado");
        }
        
        pet.setImagem(imageName);
        return PetResponseDTO.valueOf(pet);
    } */

    private void mapPetFromDTO(Pet pet, PetDTO dto, TipoAnimal tipoAnimal) {
        pet.setNome(dto.nome());
        pet.setUsuario(dto.usuario());
        pet.setAnoNascimento(dto.anoNascimento());
        pet.setTipoAnimal(tipoAnimal);
    }
}
