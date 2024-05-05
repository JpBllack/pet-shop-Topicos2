package br.projeto.petshop.service;

import java.util.List;

import org.jboss.logging.Logger;

import br.projeto.petshop.dto.PetDTO;
import br.projeto.petshop.dto.PetResponseDTO;
import br.projeto.petshop.dto.TipoAnimalDTO;
import br.projeto.petshop.model.Pet;
import br.projeto.petshop.model.TipoAnimal;
import br.projeto.petshop.repository.PetRepository;
import br.projeto.petshop.repository.TipoAnimalRepository;
import br.projeto.petshop.repository.UsuarioRepository;
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

    @Inject
    private UsuarioRepository usuarioRepository;

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
    
    Pet existingPet = petRepository.findByNome(petDTO.nome());
    if (existingPet != null) {
        throw new ValidationException("400", "Um pet com este nome já existe");
    }

    
    if (petDTO.tipoAnimal() == null) {
        throw new ValidationException("400", "O ID de TipoAnimal é obrigatório e não pode ser nulo");
    }



    Pet pet = new Pet();
    pet.setNome(petDTO.nome());
    pet.setUsuario(usuarioRepository.findById(petDTO.usuario()));
    pet.setAnoNascimento(petDTO.anoNascimento());
    pet.setTipoAnimal(tipoAnimalRepository.findById(petDTO.tipoAnimal()));


    petRepository.persist(pet);
    
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
        TipoAnimal tipoAnimal = tipoAnimalRepository.findById(dto.tipoAnimal());
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
        pet.setUsuario(usuarioRepository.findById(dto.usuario()));
        pet.setAnoNascimento(dto.anoNascimento());
        pet.setTipoAnimal(tipoAnimal);
    }
}
